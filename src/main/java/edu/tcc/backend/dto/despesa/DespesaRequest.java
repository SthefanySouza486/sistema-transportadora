package edu.tcc.backend.dto.despesa;

import edu.tcc.backend.model.despesa.CategoriaDespesa;
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
public class DespesaRequest {

    @NotNull(message = "O id da viagem é obrigatório.")
    private Long viagemId;

    @NotNull(message = "A categoria da despesa é obrigatório.")
    private CategoriaDespesa categoria;

    @NotNull(message = "A data da despesa é obrigatório.")
    private LocalDate dataDespesa;

    private String descricao;

    @NotNull(message = "O valor da despesa é obrigatório.")
    private BigDecimal valor;

    @NotNull(message = "A foto do comprovante é obrigatório.")
    private String fotoComprovante;
}
