package edu.tcc.backend.dto.veiculo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoRequest {

    @NotNull(message = "A placa do veiculo é obrigatório.")
    private String placa;

    private String placaCarreta1;

    private String placaCarreta2;
}
