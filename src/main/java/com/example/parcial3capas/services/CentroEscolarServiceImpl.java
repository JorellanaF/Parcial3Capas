package com.example.parcial3capas.services;

import com.example.parcial3capas.domain.CentroEscolar;
import com.example.parcial3capas.repositories.CentroEscolarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CentroEscolarServiceImpl implements CentroEscolarService{

    @Autowired
    CentroEscolarRepo centroEscolarRepo;

    @Override
    @Transactional
    public List<CentroEscolar> findAll() throws DataAccessException {
        return centroEscolarRepo.findAll();
    }
}
