package com.lavanderia.lavanderia.dto;

import com.lavanderia.lavanderia.entity.estadoOrden;
import jakarta.validation.constraints.NotNull;

public record cambioEstadoRequest(

        @NotNull(message = "El estado es obligatorio")
        estadoOrden estado
) {
}
