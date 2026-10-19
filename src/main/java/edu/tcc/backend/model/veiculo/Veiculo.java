package edu.tcc.backend.model.veiculo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "placa")
    private String placa;


    @Column(name = "placa_carreta1")
    private String placaCarreta1;

    @Column(name = "placa_carreta2")
    private String placaCarreta2;
}
