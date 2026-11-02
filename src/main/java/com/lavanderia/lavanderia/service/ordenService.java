package com.lavanderia.lavanderia.service;

import com.lavanderia.lavanderia.dto.ordenRequest;
import com.lavanderia.lavanderia.dto.ordenResponse;
import com.lavanderia.lavanderia.dto.totalDiaResponse;
import com.lavanderia.lavanderia.entity.estadoOrden;
import com.lavanderia.lavanderia.entity.ordenLavanderia;
import com.lavanderia.lavanderia.entity.tipoServicio;
import com.lavanderia.lavanderia.exception.recursoNoEncontradoException;
import com.lavanderia.lavanderia.exception.reglaNegocioException;
import com.lavanderia.lavanderia.repository.ordenLavanderiaRepository;
import com.lavanderia.lavanderia.repository.tipoServicioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class ordenService {

    private final ordenLavanderiaRepository ordenRepository;
    private final tipoServicioRepository tipoRepository;

    public ordenService(ordenLavanderiaRepository ordenRepository, tipoServicioRepository tipoRepository) {
        this.ordenRepository = ordenRepository;
        this.tipoRepository = tipoRepository;
    }

    public ordenResponse crear(ordenRequest request) {
        ordenLavanderia orden = new ordenLavanderia();
        orden.setEstado(estadoOrden.RECIBIDA);
        orden.setFechaRecepcion(LocalDate.now());
        copiarDatos(orden, request);
        return aRespuesta(ordenRepository.save(orden));
    }

    public List<ordenResponse> listar() {
        return ordenRepository.findAll().stream().map(this::aRespuesta).toList();
    }

    public ordenResponse buscarPorId(Long id) {
        return aRespuesta(obtener(id));
    }

    public ordenResponse modificar(Long id, ordenRequest request) {
        ordenLavanderia orden = obtener(id);
        if (orden.getEstado() == estadoOrden.ENTREGADA) {
            throw new reglaNegocioException("No se puede modificar una orden ya entregada");
        }
        copiarDatos(orden, request);
        return aRespuesta(ordenRepository.save(orden));
    }

    public void eliminar(Long id) {
        ordenLavanderia orden = obtener(id);
        if (orden.getEstado() == estadoOrden.ENTREGADA) {
            throw new reglaNegocioException("No se puede eliminar una orden ya entregada");
        }
        ordenRepository.delete(orden);
    }

    public ordenResponse cambiarEstado(Long id, estadoOrden nuevoEstado) {
        ordenLavanderia orden = obtener(id);
        estadoOrden actual = orden.getEstado();
        if (nuevoEstado.ordinal() != actual.ordinal() + 1) {
            throw new reglaNegocioException(
                    "Cambio de estado no permitido: de " + actual + " solo se puede avanzar al siguiente estado");
        }
        orden.setEstado(nuevoEstado);
        return aRespuesta(ordenRepository.save(orden));
    }

    public List<ordenResponse> listarPorEstado(estadoOrden estado) {
        return ordenRepository.findByEstado(estado).stream().map(this::aRespuesta).toList();
    }

    public List<ordenResponse> listarPorCliente(String cliente) {
        return ordenRepository.findByClienteContainingIgnoreCase(cliente).stream().map(this::aRespuesta).toList();
    }

    public List<ordenResponse> listarListasParaEntrega() {
        return listarPorEstado(estadoOrden.LISTA);
    }

    public totalDiaResponse totalDelDia() {
        LocalDate hoy = LocalDate.now();
        return new totalDiaResponse(hoy, ordenRepository.totalDelDia(hoy));
    }

    private ordenLavanderia obtener(Long id) {
        return ordenRepository.findById(id)
                .orElseThrow(() -> new recursoNoEncontradoException("No existe la orden con id " + id));
    }

    private void copiarDatos(ordenLavanderia orden, ordenRequest request) {
        tipoServicio tipo = tipoRepository.findById(request.tipoServicioId())
                .orElseThrow(() -> new recursoNoEncontradoException(
                        "No existe el tipo de servicio con id " + request.tipoServicioId()));
        if (!tipo.getActivo()) {
            throw new reglaNegocioException("El tipo de servicio seleccionado está desactivado");
        }
        orden.setCliente(request.cliente().trim());
        orden.setPesoLibras(request.pesoLibras());
        orden.setTipoServicio(tipo);
        orden.setTotal(request.pesoLibras().multiply(tipo.getPrecioPorLibra()).setScale(2, RoundingMode.HALF_UP));
    }

    private ordenResponse aRespuesta(ordenLavanderia orden) {
        return new ordenResponse(
                orden.getId(),
                orden.getCliente(),
                orden.getPesoLibras(),
                orden.getTotal(),
                orden.getEstado(),
                orden.getFechaRecepcion(),
                orden.getTipoServicio().getId(),
                orden.getTipoServicio().getNombre());
    }
}