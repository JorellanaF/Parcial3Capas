package com.example.parcial3capas.repositories;

import com.example.parcial3capas.domain.CentroEscolar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CentroEscolarRepo extends JpaRepository<CentroEscolar, Integer> {

    @Query(nativeQuery=true, value="SELECT * FROM public.centro_escolar c WHERE c.c_centro_escolar = :ID")
    CentroEscolar findByCodigoC(Integer ID);
}
