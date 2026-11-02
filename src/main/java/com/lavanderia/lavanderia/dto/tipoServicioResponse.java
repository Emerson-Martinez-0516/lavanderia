package com.lavanderia.lavanderia.dto;

import java.math.BigDecimal;

public record tipoServicioResponse(
        Long id,
        String nombre,
        BigDecimal precioPorLibra,
        Boolean activo
) {
}