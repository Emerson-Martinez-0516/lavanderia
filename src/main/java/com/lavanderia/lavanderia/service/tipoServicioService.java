package com.lavanderia.lavanderia.service;


import com.lavanderia.lavanderia.dto.tipoServicioRequest;
import com.lavanderia.lavanderia.dto.tipoServicioResponse;
import com.lavanderia.lavanderia.entity.tipoServicio;
import com.lavanderia.lavanderia.exception.recursoNoEncontradoException;
import com.lavanderia.lavanderia.exception.registroDuplicadoException;
import com.lavanderia.lavanderia.repository.tipoServicioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class tipoServicioService {

    private final tipoServicioRepository repository;

    public tipoServicioService(tipoServicioRepository repository) {
        this.repository = repository;
    }

    public tipoServicioResponse crear(tipoServicioRequest request) {
        if (repository.existsByNombreIgnoreCase(request.nombre())) {
            throw new registroDuplicadoException("Ya existe un tipo de servicio con ese nombre");
        }
        tipoServicio tipo = new tipoServicio();
        copiarDatos(tipo, request);
        return aRespuesta(repository.save(tipo));
    }

    public List<tipoServicioResponse> listar() {
        return repository.findAll().stream().map(this::aRespuesta).toList();
    }

    public tipoServicioResponse buscarPorId(Long id) {
        return aRespuesta(obtener(id));
    }

    public tipoServicioResponse modificar(Long id, tipoServicioRequest request) {
        tipoServicio tipo = obtener(id);
        if (repository.existsByNombreIgnoreCaseAndIdNot(request.nombre(), id)) {
            throw new registroDuplicadoException("Ya existe un tipo de servicio con ese nombre");
        }
        copiarDatos(tipo, request);
        return aRespuesta(repository.save(tipo));
    }

    public void desactivar(Long id) {
        tipoServicio tipo = obtener(id);
        tipo.setActivo(false);
        repository.save(tipo);
    }

    private tipoServicio obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new recursoNoEncontradoException("No existe el tipo de servicio con id " + id));
    }

    private void copiarDatos(tipoServicio tipo, tipoServicioRequest request) {
        tipo.setNombre(request.nombre().trim());
        tipo.setPrecioPorLibra(request.precioPorLibra());
        if (request.activo() != null) {
            tipo.setActivo(request.activo());
        }
    }

    private tipoServicioResponse aRespuesta(tipoServicio tipo) {
        return new tipoServicioResponse(tipo.getId(), tipo.getNombre(), tipo.getPrecioPorLibra(), tipo.getActivo());
    }
}