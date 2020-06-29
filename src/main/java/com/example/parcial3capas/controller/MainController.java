package com.example.parcial3capas.controller;

import com.example.parcial3capas.domain.Departamento;
import com.example.parcial3capas.domain.Municipio;
import com.example.parcial3capas.domain.Rol;
import com.example.parcial3capas.domain.Usuario;
import com.example.parcial3capas.repositories.DepartamentoRepo;
import com.example.parcial3capas.repositories.MunicipioRepo;
import com.example.parcial3capas.repositories.RolRepo;
import com.example.parcial3capas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.html.HTML;
import javax.validation.Valid;
import javax.xml.transform.dom.DOMSource;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private DepartamentoRepo departamentoRepo;

    @Autowired
    private MunicipioRepo municipioRepo;

    @Autowired
    private RolRepo rolRepo;

    @Autowired
    private UsuarioService usuarioService;

    //Pagina principal LOGIN
    @RequestMapping("/")
    private ModelAndView home(){
        ModelAndView mav = new ModelAndView();

        mav.setViewName("login");

        return mav;
    }

    //Pagina de REGISTRO
    @RequestMapping("/registro")
    private ModelAndView registro(){
        ModelAndView mav = new ModelAndView();
        List<Departamento> departamentos = null;
        List<Municipio> municipios = null;
        List<Rol> roles = null;

        try {
            departamentos = departamentoRepo.findAll();
            municipios = municipioRepo.municipios();
            roles = rolRepo.findAll();
        }catch(Exception e) {
            e.printStackTrace();
        }

        mav.addObject("usuario", new Usuario());
        mav.addObject("departamentos", departamentos);
        mav.addObject("municipios", municipios);
        mav.addObject("roles", roles);
        mav.setViewName("registro");

        return mav;
    }

    @RequestMapping("/admin")
    private ModelAndView admin(){
        ModelAndView mav = new ModelAndView();

        mav.setViewName("admin");

        return mav;
    }

    @RequestMapping("/coordinador")
    private ModelAndView coordinador(){
        ModelAndView mav = new ModelAndView();

        mav.setViewName("coordinador");

        return mav;
    }

    @RequestMapping("/exito")
    private ModelAndView exito(){
        ModelAndView mav = new ModelAndView();

        mav.setViewName("exito");

        return mav;
    }


    //Validando el formularo de Registro
    @RequestMapping("/validarR")
    private ModelAndView validarR(@Valid @ModelAttribute Usuario usuario, BindingResult result){
        ModelAndView mav = new ModelAndView();
        List<Departamento> departamentos = null;
        List<Municipio> municipios = null;
        List<Rol> roles = null;

        try {
            departamentos = departamentoRepo.findAll();
            municipios = municipioRepo.municipios();
            roles = rolRepo.findAll();
        }catch(Exception e) {
            e.printStackTrace();
        }

        if(result.hasErrors()){
            mav.addObject("departamentos", departamentos);
            mav.addObject("municipios", municipios);
            mav.addObject("roles", roles);
            mav.setViewName("registro");
        }else {
            try {
                usuarioService.insert(usuario);
            }catch(Exception e) {
                e.printStackTrace();
            }
            mav.setViewName("admin");
        }

        return mav;
    }

    //Validando el formulario de LOGIN
    @RequestMapping("/validDash")
    private ModelAndView validarDash(@RequestParam(value = "usuario") String user, @RequestParam(value = "password") String pass){
        ModelAndView mav = new ModelAndView();

        if(user!= null && pass!= null){
            Usuario usuario = usuarioService.findByUsuarioAndContraseña(user, pass);
            if(usuario != null){
                if(usuario.getRol().getRol().equals("Administrador")){
                    mav.setViewName("admin");
                } else{
                    mav.setViewName("coordinador");
                }
            } else {
                mav.setViewName("login");
            }
        } else {
            mav.setViewName("login");
        }

        return mav;
    }

    //*******************************Listado de Usuarios*******************************
    @RequestMapping("/listadoU")
    private ModelAndView listadoU(){
        ModelAndView mav = new ModelAndView();
        List<Departamento> departamentos = null;
        List<Municipio> municipios = null;
        List<Rol> roles = null;
        List<Usuario> usuarios = usuarioService.findAll();

        try {
            departamentos = departamentoRepo.findAll();
            municipios = municipioRepo.municipios();
            roles = rolRepo.findAll();
        }catch(Exception e) {
            e.printStackTrace();
        }

        mav.addObject("usuarios", usuarios);
        mav.addObject("departamentos", departamentos);
        mav.addObject("municipios", municipios);
        mav.addObject("roles", roles);
        mav.setViewName("listaUsuarios");

        return mav;
    }

    //*******************************Listado EDITADO de Usuarios*******************************
    @RequestMapping("/editUsuario")
    private ModelAndView editU(@RequestParam(value = "codigoUF") String codigoU, @RequestParam(value = "nombreF") String nombre, @RequestParam(value = "apellidoF") String apellido,
                               @RequestParam(value = "fechaF") String fechaNacimiento, @RequestParam(value = "direccionF") String direccionResidencia,
                               @RequestParam(value = "estadoF") String estado, @RequestParam(value = "rolF") String rol, @RequestParam(value = "departamentoF") String departamento){
        ModelAndView mav = new ModelAndView();

        Integer codigo = Integer.parseInt(codigoU);
        Integer roldd = Integer.parseInt(rol);

        Usuario usuarioAct = usuarioService.findByID(codigo);

        usuarioAct.setNombre(nombre);
        usuarioAct.setApellido(apellido);
        usuarioAct.setFechaNacimiento(fechaNacimiento);
        usuarioAct.setDireccionResidencia(direccionResidencia);
        usuarioAct.setEstado(estado);
        usuarioAct.setCodigoRol(roldd);
        usuarioAct.setCodigoDepartamento(departamentoRepo.findByDepartamento(departamento).getCodigoDepartamento());


        Usuario usuarioE = usuarioAct;
        usuarioService.insert(usuarioE);

        mav.setViewName("exito");

        return mav;
    }
    //*******************************Listado EDITADO de Usuarios*******************************
}

