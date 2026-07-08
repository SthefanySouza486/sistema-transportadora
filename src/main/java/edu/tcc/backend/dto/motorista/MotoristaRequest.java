package edu.tcc.backend.dto.motorista;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MotoristaRequest {

    @NotBlank(message = "O nome do motorista é obrigatório.")
    private String nome;

    private String telefone;

    @NotBlank(message = "A senha é obrigatório.")
    private String senha;

    @NotNull(message = "A comissão do motorista é obrigatório.")
    private BigDecimal comissao;
}
