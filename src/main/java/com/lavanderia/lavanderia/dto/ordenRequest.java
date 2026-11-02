package com.lavanderia.lavanderia.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ordenRequest(

        @NotBlank(message = "El nombre del cliente es obligatorio")
        @Size(max = 100, message = "El cliente no puede pasar de 100 caracteres")
        String cliente,

        @NotNull(message = "El peso es obligatorio")
        @DecimalMin(value = "0.01", message = "El peso debe ser mayor que cero")
        BigDecimal pesoLibras,

        @NotNull(message = "Debe seleccionar un tipo de servicio")
        Long tipoServicioId
) {
}