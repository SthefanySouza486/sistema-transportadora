package edu.tcc.backend.model.viagem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "viagem")
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "motorista_id")
    private Long motoristaId;

    @Column(name = "veiculo_id")
    private Long veiculoId;

    @Column(name = "data_viagem")
    private LocalDate dataViagem;

    @Column(name = "origem")
    private String origem;

    @Column(name = "destino")
    private String destino;

    @Column(name = "dinheiro_entregue")
    private BigDecimal dinheiroEntregue;

    @Column(name = "peso_tonelada")
    private BigDecimal pesoTonelada;

    @Column(name = "valor_por_tonelada")
    private BigDecimal valorTonelada;

    @Column(name = "valor_total_frete")
    private BigDecimal valorFrete;

    @Column(name = "valor_comissao")
    private BigDecimal valorComissao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_2")
    private StatusViagem statusViagem;
}
