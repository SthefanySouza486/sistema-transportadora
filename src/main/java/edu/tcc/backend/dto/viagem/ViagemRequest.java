package edu.tcc.backend.dto.viagem;

import jakarta.validation.constraints.NotBlank;
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
public class ViagemRequest {

    @NotBlank(message = "O id do motorista é obrigatório.")
    private Long motoristaId;

    @NotBlank(message = "O id do veiculo é obrigatório.")
    private Long veiculoId;

    @NotNull(message = "A data que foi realizada a viagem é obrigatório.")
    private LocalDate dataViagem;

    @NotNull(message = "A origem é obrigatório.")
    private String origem;

    @NotNull(message = "O destino é obrigatório.")
    private String destino;

    private BigDecimal dinheiroEntregue;

    @NotNull(message = "O peso é obrigatório.")
    private BigDecimal pesoTonelada;

    @NotNull(message = "O valor é obrigatório.")
    private BigDecimal valorTonelada;
}
