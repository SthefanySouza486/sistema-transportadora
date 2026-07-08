package edu.tcc.backend.dto.pagamento;

import edu.tcc.backend.model.pagamento.StatusPagamento;
import jakarta.validation.constraints.NotNull;
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
public class PagamentoRequest {

    @NotNull(message = "O id do motorista é obrigatório.")
    private Long motoristaId;

    @NotNull(message = "O id do gestor é obrigatório.")
    private Long gestorId;

    @NotNull(message = "A data de pagamento é obrigatório.")
    private LocalDate dataPagamento;

    @NotNull(message = "O periodo de referência é obrigatório.")
    private LocalDate periodoReferencia;

    @NotNull(message = "O valor que foi pago é obrigatório.")
    private BigDecimal valorPago;

    private StatusPagamento statusPagamento;
}
