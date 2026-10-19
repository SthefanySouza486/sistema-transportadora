package edu.tcc.backend.dto.viagem;

import edu.tcc.backend.model.motorista.Motorista;
import edu.tcc.backend.model.viagem.Viagem;
import edu.tcc.backend.repository.MotoristaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ViagemMapper {

    private final MotoristaRepository motoristaRepository;

    public Viagem toEntity(ViagemRequest request) {
        return Viagem.builder()
                .motoristaId(request.getMotoristaId())
                .veiculoId(request.getVeiculoId())
                .dataViagem(request.getDataViagem())
                .origem(request.getOrigem())
                .destino(request.getDestino())
                .dinheiroEntregue(request.getDinheiroEntregue())
                .pesoTonelada(request.getPesoTonelada())
                .valorTonelada(request.getValorTonelada())
                .build();
    }

    public ViagemResponse toResponse(Viagem viagem) {
        String nomeMotorista = "Motorista Desconhecido";
        if (viagem.getMotoristaId() != null) {
            nomeMotorista = motoristaRepository.findById(viagem.getMotoristaId())
                    .map(Motorista::getNome)
                    .orElse("Motorista Desconhecido");
        }

        return toResponse(viagem, nomeMotorista);
    }

    public ViagemResponse toResponse(Viagem viagem, String nomeMotorista) {
        return ViagemResponse.builder()
                .id(viagem.getId())
                .motoristaId(viagem.getMotoristaId())
                .nomeMotorista(nomeMotorista)
                .veiculoId(viagem.getVeiculoId())
                .dataViagem(viagem.getDataViagem())
                .origem(viagem.getOrigem())
                .destino(viagem.getDestino())
                .dinheiroEntregue(viagem.getDinheiroEntregue())
                .pesoTonelada(viagem.getPesoTonelada())
                .valorTonelada(viagem.getValorTonelada())
                .valorFrete(viagem.getValorFrete())
                .valorComissao(viagem.getValorComissao())
                .statusViagem(viagem.getStatusViagem())
                .build();
    }

    public List<ViagemResponse> toResponseList(final List<Viagem> viagens) {
        if (viagens == null || viagens.isEmpty()) {
            return List.of();
        }
        Map<Long, String> motoristasMap = motoristaRepository.findAll().stream()
                .collect(Collectors.toMap(Motorista::getId, Motorista::getNome, (a, b) -> a));

        return viagens.stream()
                .map(v -> toResponse(v, v.getMotoristaId() != null ? motoristasMap.getOrDefault(v.getMotoristaId(), "Motorista Desconhecido") : "Motorista Desconhecido"))
                .collect(Collectors.toList());
    }
}
