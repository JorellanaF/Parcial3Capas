package com.example.parcial3capas.services;

import com.example.parcial3capas.domain.Usuario;
import com.example.parcial3capas.repositories.RolRepo;
import com.example.parcial3capas.repositories.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    UsuarioRepo usuarioRepo;

    @Autowired
    RolService rolService;

    @Autowired
    DepartamentoService departamentoService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    @Transactional
    public void insert(Usuario usuario) throws DataAccessException {
        String f_nacimiento = usuario.getFechaNacimiento();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNac = LocalDate.parse(f_nacimiento, fmt);
        LocalDate hoy = LocalDate.now();
        Period periodo = Period.between(fechaNac, hoy);
        Integer edad = periodo.getYears();
        usuario.setEdad(edad);

        usuario.setRol(rolService.findById(usuario.getCodigoRol()));

        usuario.setDepartamento(departamentoService.findById(usuario.getCodigoDepartamento()));

        usuario.setContraseña(encoder.encode(usuario.getContraseña()));
        usuarioRepo.save(usuario);
    }

    @Override
    @Transactional
    public Usuario findByUsuarioAndContraseña(String username, String password) throws DataAccessException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Usuario usuario = usuarioRepo.findByUsuario(username);

        System.out.println("Impl -> " + password);
        System.out.println("Impl -> " + usuario.getContraseña());

        if(bCryptPasswordEncoder.matches(password, usuario.getContraseña())){
            return usuario;
        } else {
            return null;
        }

    }

    @Override
    @Transactional
    public Usuario findByUsername(String username) throws DataAccessException {
        Usuario usuario = usuarioRepo.findByUsuario(username);
        return usuario;
    }

    @Override
    @Transactional
    public String findRol(String usuario) throws DataAccessException {
        Integer cRol = usuarioRepo.findRol(usuario).getCodigoRol();
        String rol = rolService.findById(cRol).getRol();
        return rol;
    }

    @Override
    public List<Usuario> findAll() throws DataAccessException {
        return usuarioRepo.findAll();
    }

    @Override
    public Usuario findByID(Integer ID) throws DataAccessException {
        return usuarioRepo.findByCodigoUsuario(ID);
    }
}
