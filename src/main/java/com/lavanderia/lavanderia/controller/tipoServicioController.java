package com.lavanderia.lavanderia.controller;

import com.lavanderia.lavanderia.dto.tipoServicioRequest;
import com.lavanderia.lavanderia.dto.tipoServicioResponse;
import com.lavanderia.lavanderia.service.tipoServicioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-servicio")
public class tipoServicioController {

    private final tipoServicioService service;

    public tipoServicioController(tipoServicioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<tipoServicioResponse> crear(@Valid @RequestBody tipoServicioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(request));
    }

    @GetMapping
    public List<tipoServicioResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public tipoServicioResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public tipoServicioResponse modificar(@PathVariable Long id, @Valid @RequestBody tipoServicioRequest request) {
        return service.modificar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable Long id) {
        service.desactivar(id);
        return ResponseEntity.noContent().build();
    }
}