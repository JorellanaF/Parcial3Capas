package com.example.parcial3capas.services;

import com.example.parcial3capas.domain.Rol;
import org.springframework.dao.DataAccessException;

import java.util.Optional;

public interface RolService {
    Rol findById(Integer id) throws DataAccessException;
}
