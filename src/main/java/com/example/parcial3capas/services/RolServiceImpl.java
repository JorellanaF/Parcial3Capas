package com.example.parcial3capas.services;

import com.example.parcial3capas.domain.Rol;
import com.example.parcial3capas.repositories.RolRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RolServiceImpl implements RolService{

    @Autowired
    RolRepo rolRepo;

    @Override
    @Transactional
    public Rol findById(Integer id) throws DataAccessException {
        Rol rol = rolRepo.getOne(id);
        return rol;
    }
}
