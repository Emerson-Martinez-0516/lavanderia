package com.lavanderia.lavanderia.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record tipoServicioRequest(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 60, message = "El nombre no puede pasar de 60 caracteres")
        String nombre,

        @NotNull(message = "El precio por libra es obligatorio")
        @DecimalMin(value = "0.01", message = "El precio por libra debe ser mayor que cero")
        BigDecimal precioPorLibra,

        Boolean activo
) {
}
