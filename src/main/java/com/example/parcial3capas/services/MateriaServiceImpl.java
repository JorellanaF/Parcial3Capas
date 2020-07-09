package com.example.parcial3capas.services;

import com.example.parcial3capas.domain.Materia;
import com.example.parcial3capas.repositories.MateriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MateriaServiceImpl implements MateriaService{

    @Autowired
    MateriaRepo materiaRepo;

    @Override
    @Transactional
    public List<Materia> findAll() throws DataAccessException {
        return materiaRepo.findAll();
    }

    @Override
    @Transactional
    public List<Materia> findAllAsc() throws DataAccessException {
        return materiaRepo.findAllByOrderByCodigoMateriaAsc();
    }

    @Override
    @Transactional
    public void insert(Materia materia) throws DataAccessException {
        materiaRepo.save(materia);
    }

    @Override
    @Transactional
    public Materia findByID(Integer ID) throws DataAccessException {
        return materiaRepo.findByCodigoC(ID);
    }
}
