package com.example.parcial3capas.services;

import com.example.parcial3capas.domain.Municipio;
import org.springframework.dao.DataAccessException;

public interface MunicipioService {
    Municipio findMunicipio(String municipio) throws DataAccessException;
}
