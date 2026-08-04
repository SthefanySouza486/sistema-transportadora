package edu.tcc.backend.model.despesa;

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
@Table(name = "despesas")
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "viagem_id")
    private Long viagemId;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria")
    private CategoriaDespesa categoria;

    @Column(name = "data_despesa")
    private LocalDate dataDespesa;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "foto_comprovante")
    private String fotoComprovante;
}
