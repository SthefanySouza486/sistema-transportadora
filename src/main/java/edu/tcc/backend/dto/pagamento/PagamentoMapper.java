package edu.tcc.backend.dto.pagamento;


import edu.tcc.backend.model.pagamento.Pagamento;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PagamentoMapper {

    public Pagamento toEntity(PagamentoRequest request) {
        return Pagamento.builder()
                .motoristaId(request.getMotoristaId())
                .gestorId(request.getGestorId())
                .dataPagamento(request.getDataPagamento())
                .periodoReferencia(request.getPeriodoReferencia())
                .valorPago(request.getValorPago())
                .statusPagamento(request.getStatusPagamento())
                .build();
    }

    public PagamentoResponse toResponse (Pagamento pagamento) {
        return PagamentoResponse.builder()
                .id(pagamento.getId())
                .motoristaId(pagamento.getMotoristaId())
                .gestorId(pagamento.getGestorId())
                .dataPagamento(pagamento.getDataPagamento())
                .periodoReferencia(pagamento.getPeriodoReferencia())
                .valorPago(pagamento.getValorPago())
                .statusPagamento(pagamento.getStatusPagamento())
                .build();
    }

    public List<PagamentoResponse> toResponseList(final List<Pagamento> pagamentos) {
        if (pagamentos == null || pagamentos.isEmpty()) {
            return List.of();
        }
        return pagamentos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
