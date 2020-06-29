package com.example.parcial3capas.services;

import com.example.parcial3capas.domain.Departamento;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

public interface DepartamentoService {
    Departamento findById(Integer id) throws DataAccessException;
}
