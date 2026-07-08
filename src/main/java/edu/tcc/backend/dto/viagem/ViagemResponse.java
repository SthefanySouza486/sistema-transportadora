package edu.tcc.backend.dto.viagem;

import edu.tcc.backend.model.viagem.StatusViagem;
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
public class ViagemResponse {

    private Long id;
    private Long motoristaId;
    private Long veiculoId;
    private LocalDate dataViagem;
    private String origem;
    private String destino;
    private BigDecimal dinheiroEntregue;
    private BigDecimal pesoTonelada;
    private BigDecimal valorTonelada;
    private BigDecimal valorFrete;
    private BigDecimal valorComissao;
    private StatusViagem statusViagem;
}
