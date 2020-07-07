package com.example.parcial3capas.services;

import com.example.parcial3capas.domain.Estudiante;import com.example.parcial3capas.domain.Usuario;
import com.example.parcial3capas.dto.EstudianteDTO;
import com.example.parcial3capas.dto.MateriaDTO;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface EstudianteService {
    void insert(Estudiante estudiante) throws DataAccessException;
    List<Estudiante> findAllAsc() throws DataAccessException;
    List<EstudianteDTO> dtoEstudiante() throws DataAccessException;
    List<EstudianteDTO> dtoEstudianteByNombre(String nombre) throws DataAccessException;
    List<EstudianteDTO> dtoEstudianteByApellido(String apellido) throws DataAccessException;
    List<MateriaDTO> dtoMateriaByEstudiante(Integer ID) throws DataAccessException;
    MateriaDTO dtoMateriaByCodigoMateria(Integer ID) throws DataAccessException;
    Estudiante findByID(Integer ID) throws DataAccessException;
}
