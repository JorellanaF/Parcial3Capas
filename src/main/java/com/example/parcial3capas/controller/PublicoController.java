package com.example.parcial3capas.controller;

import com.example.parcial3capas.domain.*;
import com.example.parcial3capas.repositories.DepartamentoRepo;
import com.example.parcial3capas.repositories.MunicipioRepo;
import com.example.parcial3capas.repositories.RolRepo;
import com.example.parcial3capas.services.CentroEscolarService;
import com.example.parcial3capas.services.MateriaService;
import com.example.parcial3capas.services.MunicipioService;
import com.example.parcial3capas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PublicoController {

    @Autowired
    private DepartamentoRepo departamentoRepo;

    @Autowired
    private MunicipioRepo municipioRepo;

    @Autowired
    MunicipioService municipioService;

    @Autowired
    private RolRepo rolRepo;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    CentroEscolarService centroEscolarService;

    @Autowired
    MateriaService materiaService;

    @RequestMapping("/admin")
    public ModelAndView admin(){
        ModelAndView mav = new ModelAndView();

        mav.setViewName("admin");

        return mav;
    }

    @RequestMapping("/coordinador")
    public ModelAndView coordinador(){
        ModelAndView mav = new ModelAndView();

        mav.setViewName("coordinador");

        return mav;
    }

    @RequestMapping("/exito")
    public ModelAndView exito(){
        ModelAndView mav = new ModelAndView();

        mav.setViewName("exito");

        return mav;
    }

    //*******************************LOGIN*******************************
    //Pagina principal LOGIN
    @RequestMapping("/")
    public ModelAndView home(){
        ModelAndView mav = new ModelAndView();

        mav.setViewName("login");

        return mav;
    }

    //Validando el formulario de LOGIN
    @RequestMapping("/validDash")
    public ModelAndView validarDash(@RequestParam(value = "usuario") String user, @RequestParam(value = "password") String pass){
        ModelAndView mav = new ModelAndView();

        if(user!= null && pass!= null){
            Usuario usuario = usuarioService.findByUsuarioAndContraseña(user, pass);
            if(usuario != null){
                if(usuario.getEstado().equals("Activo")){
                    if(usuario.getRol().getRol().equals("Administrador")){
                        mav.setViewName("admin");
                    } else{
                        mav.setViewName("coordinador");
                    }
                } else {
                    mav.addObject("Inactivo", "No tiene acceso al sistema. Su cuenta esta Inactiva");
                    mav.setViewName("login");
                }
            } else {
                mav.setViewName("login");
            }
        } else {
            mav.setViewName("login");
        }

        return mav;
    }
    //*******************************LOGIN*******************************

    //*******************************Usuario*******************************
    //Pagina de REGISTRO PUBLICO
    @RequestMapping("/registroP")
    public ModelAndView registroP(){
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
        //mav.addObject("roles", roles);
        mav.setViewName("registroP");

        return mav;
    }
    //Pagina de REGISTRO PUBLICO

    //Pagina de VALIDAR PUBLICO
    @RequestMapping("/validarRP")
    public ModelAndView validarRP(@RequestParam(value = "nombre") String nombre, @RequestParam(value = "apellido") String apellido, @RequestParam(value = "fechaNacimiento") String fechaNacimiento,
                                  @RequestParam(value = "codigoDepartamento") String codigoDepartamento, @RequestParam(value = "direccionResidencia") String direccionResidencia,
                                  @RequestParam(value = "usuario") String usuario, @RequestParam(value = "contraseña") String contraseña){
        ModelAndView mav = new ModelAndView();
        List<Departamento> departamentos = null;
        List<Municipio> municipios = null;

        Usuario usuarioE = usuarioService.findByUsername(usuario);

        try {
            departamentos = departamentoRepo.findAll();
            municipios = municipioRepo.municipios();
        }catch(Exception e) {
            e.printStackTrace();
        }

        if(usuarioE == null){
            Usuario usuarioC = new Usuario();
            usuarioC.setNombre(nombre);
            usuarioC.setApellido(apellido);
            usuarioC.setFechaNacimiento(fechaNacimiento);
            usuarioC.setCodigoDepartamento(departamentoRepo.findByCodigoDepartamento(Integer.parseInt(codigoDepartamento)).getCodigoDepartamento());
            usuarioC.setDireccionResidencia(direccionResidencia);
            usuarioC.setCodigoRol(2);
            usuarioC.setEstado("Inactivo");
            usuarioC.setUsuario(usuario);
            usuarioC.setContraseña(contraseña);
            usuarioService.insert(usuarioC);
            mav.addObject("usuarios", usuarioService.findAllAsc());
            mav.setViewName("login");
        } else {
            mav.addObject("error", "Este usuario ya existe. Por favor ingrese otro.");
            mav.addObject("departamentos", departamentos);
            mav.addObject("municipios", municipios);
            mav.setViewName("registroP");
        }

        return mav;
    }
    //Pagina de VALIDAR PUBLICO

}
