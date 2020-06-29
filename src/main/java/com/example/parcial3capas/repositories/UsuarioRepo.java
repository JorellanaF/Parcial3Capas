package com.example.parcial3capas.repositories;


import com.example.parcial3capas.domain.Usuario;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

    Usuario findByUsuario(String usuario);

    Usuario findByCodigoUsuario(Integer ID);

    @Query(nativeQuery=true, value="SELECT u.c_rol FROM public.usuario u WHERE u.usuario = :username")
    Usuario findRol(@Param("username") String username) throws DataAccessException;

    @Query(nativeQuery=true, value="select * from public.usuario u where u.usuario = :username and u.contraseña = :password")
    Usuario findByUsuarioAndContraseña(String username, String password) throws DataAccessException;
}
