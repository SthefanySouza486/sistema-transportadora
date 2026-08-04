package edu.tcc.backend.model.pagamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "motorista_id")
    private Long motoristaId;

    @Column(name = "gestor_id")
    private Long gestorId;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Column(name = "periodo_referencia")
    private LocalDate periodoReferencia;

    @Column(name = "valor_pago")
    private BigDecimal valorPago;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_2")
    private StatusPagamento statusPagamento;
}
