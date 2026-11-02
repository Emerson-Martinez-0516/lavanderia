package com.lavanderia.lavanderia.repository;

import com.lavanderia.lavanderia.entity.tipoServicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface tipoServicioRepository extends JpaRepository<tipoServicio, Long> {

    boolean existsByNombreIgnoreCase(String nombre);

    boolean existsByNombreIgnoreCaseAndIdNot(String nombre, Long id);
}