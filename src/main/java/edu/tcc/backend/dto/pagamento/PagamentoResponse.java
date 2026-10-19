package edu.tcc.backend.dto.pagamento;

import edu.tcc.backend.model.pagamento.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoResponse {

    private Long id;
    private Long motoristaId;
    private String nomeMotorista;
    private Long gestorId;
    private LocalDate dataPagamento;
    private String periodoReferencia;
    private BigDecimal valorPago;
    private StatusPagamento statusPagamento;
    private String fotoComprovante;
}
