package com.example.parcial3capas.services;

import com.example.parcial3capas.domain.Usuario;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UsuarioService {
    void insert(Usuario usuario) throws DataAccessException;
    Usuario findByUsuarioAndContraseña(String usuario, String contraseña) throws DataAccessException;
    Usuario findByUsername(String username) throws DataAccessException;
    String findRol(String usuario) throws DataAccessException;
    List<Usuario> findAll() throws DataAccessException;
    List<Usuario> findAllAsc() throws DataAccessException;
    Usuario findByID(Integer ID) throws DataAccessException;
}
