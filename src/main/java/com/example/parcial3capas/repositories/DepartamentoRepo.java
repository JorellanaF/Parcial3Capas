package com.example.parcial3capas.repositories;

import com.example.parcial3capas.domain.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepo extends JpaRepository<Departamento, Integer> {
    Departamento findByDepartamento(String departamento);
}
