package edu.tcc.backend.dto.despesa;

import edu.tcc.backend.model.despesa.Despesa;
import edu.tcc.backend.model.motorista.Motorista;
import edu.tcc.backend.model.viagem.Viagem;
import edu.tcc.backend.repository.MotoristaRepository;
import edu.tcc.backend.repository.ViagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DespesaMapper {

    private final ViagemRepository viagemRepository;
    private final MotoristaRepository motoristaRepository;

    public Despesa toEntity(DespesaRequest request) {
        return Despesa.builder()
                .viagemId(request.getViagemId())
                .categoria(request.getCategoria())
                .dataDespesa(request.getDataDespesa())
                .descricao(request.getDescricao())
                .valor(request.getValor())
                .fotoComprovante(request.getFotoComprovante())
                .build();
    }

    public DespesaResponse toResponse(Despesa despesa) {
        Long motoristaId = null;
        String nomeMotorista = "Não informado";

        if (despesa.getViagemId() != null) {
            Viagem viagem = viagemRepository.findById(despesa.getViagemId()).orElse(null);
            if (viagem != null && viagem.getMotoristaId() != null) {
                motoristaId = viagem.getMotoristaId();
                nomeMotorista = motoristaRepository.findById(motoristaId)
                        .map(Motorista::getNome)
                        .orElse("Motorista Desconhecido");
            }
        }

        return toResponse(despesa, motoristaId, nomeMotorista);
    }

    public DespesaResponse toResponse(Despesa despesa, Long motoristaId, String nomeMotorista) {
        return DespesaResponse.builder()
                .id(despesa.getId())
                .viagemId(despesa.getViagemId())
                .motoristaId(motoristaId)
                .nomeMotorista(nomeMotorista)
                .categoria(despesa.getCategoria())
                .dataDespesa(despesa.getDataDespesa())
                .descricao(despesa.getDescricao())
                .valor(despesa.getValor())
                .fotoComprovante(despesa.getFotoComprovante())
                .build();
    }

    public List<DespesaResponse> toResponseList(final List<Despesa> despesas) {
        if (despesas == null || despesas.isEmpty()) {
            return List.of();
        }

        Set<Long> viagemIds = despesas.stream()
                .map(Despesa::getViagemId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Map<Long, Viagem> viagemMap = viagemRepository.findAllById(viagemIds).stream()
                .collect(Collectors.toMap(Viagem::getId, Function.identity(), (a, b) -> a));

        Set<Long> motoristaIds = viagemMap.values().stream()
                .map(Viagem::getMotoristaId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Map<Long, String> motoristaMap = motoristaRepository.findAllById(motoristaIds).stream()
                .collect(Collectors.toMap(Motorista::getId, Motorista::getNome, (a, b) -> a));

        return despesas.stream()
                .map(despesa -> {
                    Viagem v = despesa.getViagemId() != null ? viagemMap.get(despesa.getViagemId()) : null;
                    Long motoristaId = v != null ? v.getMotoristaId() : null;
                    String nomeMotorista = "Não informado";
                    if (motoristaId != null) {
                        nomeMotorista = motoristaMap.getOrDefault(motoristaId, "Motorista Desconhecido");
                    }
                    return toResponse(despesa, motoristaId, nomeMotorista);
                })
                .collect(Collectors.toList());
    }
}
