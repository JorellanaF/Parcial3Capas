package com.example.parcial3capas.repositories;

import com.example.parcial3capas.domain.Estudiante;
import com.example.parcial3capas.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstudianteRepo extends JpaRepository<Estudiante, Integer> {

    //List<Estudiante> findAllByOrderByCodigoCentroAsc();
    Estudiante findByCodigoEstudiante(Integer ID);
}
