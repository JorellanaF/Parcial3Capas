package com.example.parcial3capas.services;

import com.example.parcial3capas.domain.Municipio;
import com.example.parcial3capas.repositories.MunicipioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MunicipioServiceImpl implements MunicipioService{

    @Autowired
    MunicipioRepo municipioRepo;

    @Override
    @Transactional
    public Municipio findMunicipio(String municipio) throws DataAccessException {
        return municipioRepo.findByMunicipio(municipio);
    }
}
