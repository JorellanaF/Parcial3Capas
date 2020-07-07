package com.example.parcial3capas.services;

import com.example.parcial3capas.domain.MateriaxEstudiante;
import org.springframework.dao.DataAccessException;

public interface MateriaEService {
    void insert(MateriaxEstudiante materia) throws DataAccessException;
}
