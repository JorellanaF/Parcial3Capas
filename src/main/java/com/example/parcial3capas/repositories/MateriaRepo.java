package com.example.parcial3capas.repositories;

import com.example.parcial3capas.domain.CentroEscolar;
import com.example.parcial3capas.domain.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MateriaRepo extends JpaRepository<Materia, Integer> {

    @Query(nativeQuery=true, value="SELECT * FROM public.materia m WHERE m.c_materia = :ID")
    Materia findByCodigoC(Integer ID);

    List<Materia> findAllByOrderByCodigoMateriaAsc();

}
