package edu.tcc.backend.dto.viagem;

import edu.tcc.backend.model.viagem.StatusViagem;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViagemStatusRequest {

    @NotNull(message = "O status da viagem é obrigatório.")
    private StatusViagem statusViagem;
}
