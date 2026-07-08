package edu.tcc.backend.model.veiculo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "veiculo")
public class Veiculo {

    @Id
    private Long id;

    @Column("placa")
    private String placa;

    @Column("tipo")
    private TipoVeiculo tipo;

}
