package edu.tcc.backend.dto.veiculo;

import edu.tcc.backend.model.veiculo.TipoVeiculo;
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

    @NotNull(message = "O tipo do veiculo é obrigatório.")
    private TipoVeiculo tipo;
}
