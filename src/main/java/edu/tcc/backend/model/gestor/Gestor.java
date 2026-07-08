package edu.tcc.backend.model.gestor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gestor")
public class Gestor {

    @Id
    private Long id;

    @Column("nome")
    private String nome;

    @Column("senha")
    private String senha;
}
