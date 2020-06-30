package com.example.parcial3capas.services;

import com.example.parcial3capas.domain.CentroEscolar;
import com.example.parcial3capas.domain.Usuario;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface CentroEscolarService {
    List<CentroEscolar> findAll() throws DataAccessException;
    void insert(CentroEscolar centroEscolar) throws DataAccessException;
}
