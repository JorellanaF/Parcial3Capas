package com.example.parcial3capas;

import com.example.parcial3capas.domain.Usuario;
import com.example.parcial3capas.repositories.UsuarioRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
class Parcial3capasApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    UsuarioRepo usuarioRepo;

    @Autowired
    BCryptPasswordEncoder encoder;

    /*@Test
    @Transactional
    public void crearUsuarioTest(){
        Usuario us = new Usuario();
        Calendar myObj = Calendar.getInstance();

        us.setCodigoUsuario(2);
        us.setNombre("Jose");
        us.setApellido("orellana");
        us.setFechaNacimiento(myObj.getTime());
        us.setEdad(30);
        us.setDireccionResidencia("San salvador");
        us.setEstado("Activo");
        us.setUsuario("Jorellana");
        us.setPassword(encoder.encode("123"));
        us.setCodigoRol(1);
        us.setCodigoDepartamento(10);

        Usuario usuario = usuarioRepo.save(us);

        assertTrue(usuario.getPassword().equalsIgnoreCase(us.getPassword()));
    }*/

}
