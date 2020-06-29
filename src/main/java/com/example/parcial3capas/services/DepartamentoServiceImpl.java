package com.example.parcial3capas.services;

import com.example.parcial3capas.domain.Departamento;
import com.example.parcial3capas.repositories.DepartamentoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class DepartamentoServiceImpl implements DepartamentoService{

    @Autowired
    DepartamentoRepo departamentoRepo;

    @Override
    public Departamento findById(Integer id) throws DataAccessException {
        Departamento departamento = departamentoRepo.getOne(id);
        return departamento;
    }
}
