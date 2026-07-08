package edu.tcc.backend.model.motorista;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "motorista")
public class Motorista {

    @Id
    private Long id;

    @Column("nome")
    private String nome;

    @Column("telefone")
    private String telefone;

    @Column("senha")
    private String senha;

    @Column("comissao")
    private BigDecimal comissao;
}
