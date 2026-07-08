package edu.tcc.backend.dto.despesa;

import edu.tcc.backend.model.despesa.Despesa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DespesaMapper {

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
        return DespesaResponse.builder()
                .id(despesa.getId())
                .viagemId(despesa.getViagemId())
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
        return despesas.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
