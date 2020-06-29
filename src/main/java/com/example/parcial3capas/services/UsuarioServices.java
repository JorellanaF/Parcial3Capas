package com.example.parcial3capas.services;



import com.example.parcial3capas.domain.Rol;
import com.example.parcial3capas.domain.Usuario;
import com.example.parcial3capas.repositories.RolRepo;
import com.example.parcial3capas.repositories.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class UsuarioServices implements UserDetailsService {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    RolRepo rolRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario us = usuarioRepo.findByUsuario(username);
        List<GrantedAuthority> roles = new ArrayList<>();
        Rol rol = rolRepo.getOne(us.getRol().getCodigoRol());
        roles.add(new SimpleGrantedAuthority(rol.getRol()));

        UserDetails userDetails = new User(us.getUsuario(), us.getContraseña(), roles);

        //System.out.println("UsuarioServices -> " + us.getUsuario());

        //System.out.println(rol.getRol());

        return userDetails;
    }


}
