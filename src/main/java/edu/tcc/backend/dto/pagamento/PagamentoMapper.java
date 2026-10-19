package edu.tcc.backend.dto.pagamento;


import edu.tcc.backend.model.motorista.Motorista;
import edu.tcc.backend.model.pagamento.Pagamento;
import edu.tcc.backend.repository.MotoristaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PagamentoMapper {

    private final MotoristaRepository motoristaRepository;

    public Pagamento toEntity(PagamentoRequest request) {
        LocalDate referenciaConvertida = null;
        if (request.getPeriodoReferencia() != null) {
            referenciaConvertida = LocalDate.parse(request.getPeriodoReferencia() + "-01");
        }
        return Pagamento.builder()
                .motoristaId(request.getMotoristaId())
                .gestorId(request.getGestorId())
                .dataPagamento(request.getDataPagamento())
                .periodoReferencia(referenciaConvertida)
                .valorPago(request.getValorPago())
                .statusPagamento(request.getStatusPagamento())
                .fotoComprovante(request.getFotoComprovante())
                .build();
    }

    public PagamentoResponse toResponse (Pagamento pagamento) {
        String periodoFormatado = null;
        if (pagamento.getPeriodoReferencia() != null) {
            periodoFormatado = pagamento.getPeriodoReferencia().toString().substring(0, 7);
        }

        String nomeMotorista = "Motorista Desconhecido";
        if (pagamento.getMotoristaId() != null) {
            nomeMotorista = motoristaRepository.findById(pagamento.getMotoristaId())
                    .map(Motorista::getNome)
                    .orElse("Motorista Desconhecido");
        }

        return PagamentoResponse.builder()
                .id(pagamento.getId())
                .motoristaId(pagamento.getMotoristaId())
                .nomeMotorista(nomeMotorista)
                .gestorId(pagamento.getGestorId())
                .dataPagamento(pagamento.getDataPagamento())
                .periodoReferencia(periodoFormatado)
                .valorPago(pagamento.getValorPago())
                .statusPagamento(pagamento.getStatusPagamento())
                .fotoComprovante(pagamento.getFotoComprovante())
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
