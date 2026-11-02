package com.lavanderia.lavanderia.exception;

import com.lavanderia.lavanderia.dto.errorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class manejadorExcepciones {

    @ExceptionHandler(recursoNoEncontradoException.class)
    public ResponseEntity<errorResponse> noEncontrado(recursoNoEncontradoException ex) {
        return construir(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(registroDuplicadoException.class)
    public ResponseEntity<errorResponse> duplicado(registroDuplicadoException ex) {
        return construir(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(reglaNegocioException.class)
    public ResponseEntity<errorResponse> reglaNegocio(reglaNegocioException ex) {
        return construir(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<errorResponse> datosInvalidos(MethodArgumentNotValidException ex) {
        String mensaje = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining(". "));
        return construir(HttpStatus.BAD_REQUEST, mensaje);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<errorResponse> formatoInvalido(Exception ex) {
        return construir(HttpStatus.BAD_REQUEST, "Formato de datos inválido. Revise los valores enviados");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<errorResponse> errorInesperado(Exception ex) {
        return construir(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error inesperado en el sistema");
    }

    private ResponseEntity<errorResponse> construir(HttpStatus status, String mensaje) {
        errorResponse error = new errorResponse(status.value(), mensaje, LocalDateTime.now());
        return ResponseEntity.status(status).body(error);
    }
}