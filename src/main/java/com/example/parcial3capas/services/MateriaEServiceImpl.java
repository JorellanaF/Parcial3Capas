package com.example.parcial3capas.services;

import com.example.parcial3capas.domain.MateriaxEstudiante;
import com.example.parcial3capas.repositories.EstudianteRepo;
import com.example.parcial3capas.repositories.MateriaERepo;
import com.example.parcial3capas.repositories.MateriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MateriaEServiceImpl implements MateriaEService{

    @Autowired
    MateriaERepo materiaERepo;

    @Autowired
    MateriaRepo materiaRepo;

    @Autowired
    EstudianteRepo estudianteRepo;

    @Override
    @Transactional
    public void insert(MateriaxEstudiante materia) throws DataAccessException {


        materiaERepo.save(materia);
    }
}
