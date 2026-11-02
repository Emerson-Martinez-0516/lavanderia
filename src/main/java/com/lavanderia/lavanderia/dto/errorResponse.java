package com.lavanderia.lavanderia.dto;

import java.time.LocalDateTime;

public record errorResponse(
        int status,
        String mensaje,
        LocalDateTime fecha
) {
}
