package com.example.parcial3capas.services;

import com.example.parcial3capas.domain.CentroEscolar;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface CentroEscolarService {
    List<CentroEscolar> findAll() throws DataAccessException;
    List<CentroEscolar> findAllAsc() throws DataAccessException;
    void insert(CentroEscolar centroEscolar) throws DataAccessException;
    CentroEscolar findByID(Integer ID) throws DataAccessException;
    List<CentroEscolar> findByIDM(Integer ID) throws DataAccessException;
}
