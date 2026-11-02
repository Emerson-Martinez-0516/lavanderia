package com.lavanderia.lavanderia.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "orden_lavanderia")
@Getter
@Setter
public class ordenLavanderia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String cliente;

    @Column(name = "peso_libras", nullable = false)
    private BigDecimal pesoLibras;

    @Column(nullable = false)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private estadoOrden estado = estadoOrden.RECIBIDA;

    @Column(name = "fecha_recepcion", nullable = false)
    private LocalDate fechaRecepcion = LocalDate.now();

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_servicio_id")
    private tipoServicio tipoServicio;
}
