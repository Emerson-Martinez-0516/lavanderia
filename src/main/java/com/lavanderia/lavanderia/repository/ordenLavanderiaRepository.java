package com.lavanderia.lavanderia.repository;

import com.lavanderia.lavanderia.entity.estadoOrden;
import com.lavanderia.lavanderia.entity.ordenLavanderia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ordenLavanderiaRepository extends JpaRepository<ordenLavanderia, Long> {

    List<ordenLavanderia> findByEstado(estadoOrden estado);

    List<ordenLavanderia> findByClienteContainingIgnoreCase(String cliente);

    boolean existsByTipoServicioId(Long tipoServicioId);

    @Query("SELECT COALESCE(SUM(o.total), 0) FROM ordenLavanderia o WHERE o.fechaRecepcion = :fecha")
    BigDecimal totalDelDia(LocalDate fecha);
}
