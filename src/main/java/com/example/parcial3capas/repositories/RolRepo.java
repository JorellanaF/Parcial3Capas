package com.example.parcial3capas.repositories;

import com.example.parcial3capas.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepo extends JpaRepository<Rol, Integer> {
    Rol findByCodigoRol(Integer ID);
}
