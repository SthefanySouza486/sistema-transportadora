package edu.tcc.backend.model.despesa;

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
@Table(name = "despesas")
public class Despesa {

    @Id
    private Long id;

    @Column("viagem_id")
    private Long viagemId;

    @Column("categoria")
    private CategoriaDespesa categoria;

    @Column("data_despesa")
    private LocalDate dataDespesa;

    @Column("descricao")
    private String descricao;

    @Column("valor")
    private BigDecimal valor;

    @Column("foto_comprovante")
    private String fotoComprovante;
}
