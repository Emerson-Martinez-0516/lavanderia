package com.lavanderia.lavanderia.dto;

import com.lavanderia.lavanderia.entity.estadoOrden;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ordenResponse(
        Long id,
        String cliente,
        BigDecimal pesoLibras,
        BigDecimal total,
        estadoOrden estado,
        LocalDate fechaRecepcion,
        Long tipoServicioId,
        String tipoServicioNombre
) {
}
