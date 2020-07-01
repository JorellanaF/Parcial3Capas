package com.example.parcial3capas.services;

import com.example.parcial3capas.domain.Materia;
import com.example.parcial3capas.repositories.MateriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaServiceImpl implements MateriaService{

    @Autowired
    MateriaRepo materiaRepo;

    @Override
    public List<Materia> findAll() throws DataAccessException {
        return materiaRepo.findAll();
    }

    @Override
    public List<Materia> findAllAsc() throws DataAccessException {
        return materiaRepo.findAllByOrderByCodigoMateriaAsc();
    }

    @Override
    public void insert(Materia materia) throws DataAccessException {
        materiaRepo.save(materia);
    }

    @Override
    public Materia findByID(Integer ID) throws DataAccessException {
        return materiaRepo.findByCodigoC(ID);
    }
}
