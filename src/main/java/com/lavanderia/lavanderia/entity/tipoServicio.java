package com.lavanderia.lavanderia.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tipo_servicio")
@Getter
@Setter
public class tipoServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 60)
    private String nombre;

    @Column(name = "precio_por_libra", nullable = false)
    private BigDecimal precioPorLibra;

    @Column(nullable = false)
    private Boolean activo = true;
}