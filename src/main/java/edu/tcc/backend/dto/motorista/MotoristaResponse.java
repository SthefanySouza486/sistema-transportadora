package edu.tcc.backend.dto.motorista;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MotoristaResponse {

    private Long id;
    private String nome;
    private String telefone;
    private BigDecimal comissao;
}
