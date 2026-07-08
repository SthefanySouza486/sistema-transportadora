package edu.tcc.backend.dto.veiculo;

import edu.tcc.backend.model.veiculo.TipoVeiculo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoResponse {

    private Long id;
    private String placa;
    private TipoVeiculo tipo;
}
