package com.example.parcial3capas.services;

import com.example.parcial3capas.domain.Materia;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface MateriaService {
    List<Materia> findAll() throws DataAccessException;
    List<Materia> findAllAsc() throws DataAccessException;
    void insert(Materia materia) throws DataAccessException;
    Materia findByID(Integer ID) throws DataAccessException;
}
