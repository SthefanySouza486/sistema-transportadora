package edu.tcc.backend.model.pagamento;

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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pagamento")
public class Pagamento {

    @Id
    private Long Id;

    @Column("motorista_id")
    private Long motoristaId;

    @Column("gestor_id")
    private Long gestorId;

    @Column("data_pagamento")
    private LocalDate dataPagamento;

    @Column("periodo_referencia")
    private LocalDate periodoReferencia;

    @Column("valor_pago")
    private BigDecimal valorPago;

    @Column("status_2")
    private StatusPagamento statusPagamento;
}
