package edu.tcc.backend.model.viagem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "viagem")
public class Viagem {

    @Id
    private Long id;

    @Column("motorista_id")
    private Long motoristaId;

    @Column("veiculo_id")
    private Long veiculoId;

    @Column("data_viagem")
    private LocalDate dataViagem;

    @Column("origem")
    private String origem;

    @Column("destino")
    private String destino;

    @Column("dinheiro_entregue")
    private BigDecimal dinheiroEntregue;

    @Column("peso_tonelada")
    private BigDecimal pesoTonelada;

    @Column("valor_por_tonelada")
    private BigDecimal valorTonelada;

    @Column("valor_total_frete")
    private BigDecimal valorFrete;

    @Column("valor_comissao")
    private BigDecimal valorComissao;

    @Column("status_2")
    private StatusViagem statusViagem;
}
