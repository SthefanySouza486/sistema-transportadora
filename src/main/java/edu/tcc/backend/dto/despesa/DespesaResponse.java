package edu.tcc.backend.dto.despesa;

import edu.tcc.backend.model.despesa.CategoriaDespesa;
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
public class DespesaResponse {

    private Long id;

    private Long viagemId;

    private CategoriaDespesa categoria;

    private LocalDate dataDespesa;

    private String descricao;

    private BigDecimal valor;

    private String fotoComprovante;
}
