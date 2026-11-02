package com.lavanderia.lavanderia.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record totalDiaResponse(
        LocalDate fecha,
        BigDecimal totalEsperado
) {
}