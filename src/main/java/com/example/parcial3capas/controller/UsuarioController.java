package com.example.parcial3capas.controller;

import com.example.parcial3capas.domain.Departamento;
import com.example.parcial3capas.domain.Municipio;
import com.example.parcial3capas.domain.Rol;
import com.example.parcial3capas.domain.Usuario;
import com.example.parcial3capas.repositories.DepartamentoRepo;
import com.example.parcial3capas.repositories.MunicipioRepo;
import com.example.parcial3capas.repositories.RolRepo;
import com.example.parcial3capas.services.CentroEscolarService;
import com.example.parcial3capas.services.MateriaService;
import com.example.parcial3capas.services.MunicipioService;
import com.example.parcial3capas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UsuarioController {

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

    //Pagina de REGISTRO ADMIN
    @RequestMapping("/registro")
    public ModelAndView registro(){
        ModelAndView mav = new ModelAndView();
        List<Departamento> departamentos = null;
        List<Municipio> municipios = null;
        List<Rol> roles = null;

        try {
            departamentos = departamentoRepo.findAll();
            municipios = municipioRepo.municipios();
            roles = rolRepo.findAll();
            mav.addObject("usuario", new Usuario());
            mav.addObject("departamentos", departamentos);
            mav.addObject("municipios", municipios);
            mav.addObject("roles", roles);
            mav.setViewName("registro");
        }catch(Exception e) {
            e.printStackTrace();
        }

        return mav;
    }//Pagina de REGISTRO ADMIN

    //Validando el formularo de Registro
    @RequestMapping("/validarR")
    public ModelAndView validarR(@Valid @ModelAttribute Usuario usuario, BindingResult result){
        ModelAndView mav = new ModelAndView();
        List<Departamento> departamentos = null;
        List<Municipio> municipios = null;
        List<Rol> roles = null;
        String username = usuario.getUsuario();
        Usuario usuarioE = usuarioService.findByUsername(username);
        System.out.println(usuarioE);

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
                if(usuarioE == null){
                    usuarioService.insert(usuario);
                    mav.addObject("usuarios", usuarioService.findAllAsc());
                    mav.setViewName("listaUsuarios");
                } else {
                    mav.addObject("error", "Este usuario ya existe. Por favor ingrese otro.");
                    mav.addObject("departamentos", departamentos);
                    mav.addObject("municipios", municipios);
                    mav.addObject("roles", roles);
                    mav.setViewName("registro");
                }
            }catch(Exception e) {
                e.printStackTrace();
            }
        }

        return mav;
    }

    //*******************************Listado de Usuarios*******************************
    @RequestMapping("/listadoU")
    public ModelAndView listadoU(){
        ModelAndView mav = new ModelAndView();
        List<Departamento> departamentos = null;
        List<Municipio> municipios = null;
        List<Rol> roles = null;
        List<Usuario> usuarios = usuarioService.findAllAsc();

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
    public ModelAndView editU(@RequestParam(value = "codigoUF") String codigoU, @RequestParam(value = "nombreF") String nombre, @RequestParam(value = "apellidoF") String apellido,
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

        List<Departamento> departamentos = null;
        List<Municipio> municipios = null;
        List<Rol> roles = null;
        List<Usuario> usuarios = usuarioService.findAllAsc();

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
    //*******************************Usuario*******************************
}
