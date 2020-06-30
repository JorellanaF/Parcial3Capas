package com.example.parcial3capas.repositories;

import com.example.parcial3capas.domain.Departamento;
import com.example.parcial3capas.domain.Municipio;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MunicipioRepo extends JpaRepository<Municipio, Integer> {
    @Query(nativeQuery=true, value="SELECT * FROM public.municipio ORDER BY c_municipio ASC")
    List<Municipio> municipios();

    @Query(nativeQuery=true, value="SELECT * FROM public.municipio m WHERE m.c_municipio = :ID")
    Municipio porID(Integer ID) throws DataAccessException;
}
