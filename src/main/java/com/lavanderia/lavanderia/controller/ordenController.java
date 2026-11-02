package com.lavanderia.lavanderia.controller;

import com.lavanderia.lavanderia.dto.cambioEstadoRequest;
import com.lavanderia.lavanderia.dto.ordenRequest;
import com.lavanderia.lavanderia.dto.ordenResponse;
import com.lavanderia.lavanderia.dto.totalDiaResponse;
import com.lavanderia.lavanderia.entity.estadoOrden;
import com.lavanderia.lavanderia.service.ordenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
public class ordenController {

    private final ordenService service;

    public ordenController(ordenService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ordenResponse> crear(@Valid @RequestBody ordenRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(request));
    }

    @GetMapping
    public List<ordenResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ordenResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ordenResponse modificar(@PathVariable Long id, @Valid @RequestBody ordenRequest request) {
        return service.modificar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/estado")
    public ordenResponse cambiarEstado(@PathVariable Long id, @Valid @RequestBody cambioEstadoRequest request) {
        return service.cambiarEstado(id, request.estado());
    }

    @GetMapping("/estado/{estado}")
    public List<ordenResponse> porEstado(@PathVariable estadoOrden estado) {
        return service.listarPorEstado(estado);
    }

    @GetMapping("/cliente")
    public List<ordenResponse> porCliente(@RequestParam String cliente) {
        return service.listarPorCliente(cliente);
    }

    @GetMapping("/listas")
    public List<ordenResponse> listasParaEntrega() {
        return service.listarListasParaEntrega();
    }

    @GetMapping("/total-dia")
    public totalDiaResponse totalDelDia() {
        return service.totalDelDia();
    }
}