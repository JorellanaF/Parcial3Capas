package com.example.parcial3capas.services;

import com.example.parcial3capas.domain.CentroEscolar;
import com.example.parcial3capas.domain.Usuario;
import com.example.parcial3capas.repositories.CentroEscolarRepo;
import com.example.parcial3capas.repositories.DepartamentoRepo;
import com.example.parcial3capas.repositories.MunicipioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CentroEscolarServiceImpl implements CentroEscolarService{

    @Autowired
    CentroEscolarRepo centroEscolarRepo;

    @Autowired
    MunicipioRepo municipioRepo;

    @Override
    @Transactional
    public List<CentroEscolar> findAll() throws DataAccessException {
        return centroEscolarRepo.findAll();
    }

    @Override
    public List<CentroEscolar> findAllAsc() throws DataAccessException {
        return centroEscolarRepo.findAllByOrderByCodigoCentroAsc();
    }

    @Override
    @Transactional
    public void insert(CentroEscolar centroEscolar) throws DataAccessException {
        centroEscolar.setMunicipio(municipioRepo.porID(centroEscolar.getCodigoMunicipio()));

        centroEscolarRepo.save(centroEscolar);
    }

    @Override
    public CentroEscolar findByID(Integer ID) throws DataAccessException {
        return centroEscolarRepo.findByCodigoC(ID);
    }

    @Override
    public List<CentroEscolar> findByIDM(Integer ID) throws DataAccessException {
        return centroEscolarRepo.findByCodigoMunicipio(ID);
    }
}
