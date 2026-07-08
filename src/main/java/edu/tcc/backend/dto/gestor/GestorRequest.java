package edu.tcc.backend.dto.gestor;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GestorRequest {

    @NotBlank(message = "O nome do gestor é obrigatório.")
    private String nome;

    @NotBlank(message = "A senha é obrigatório.")
    private String senha;
}
