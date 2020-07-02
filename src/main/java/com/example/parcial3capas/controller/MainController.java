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
import org.springframework.data.repository.query.Param;
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
        }catch(Exception e) {
            e.printStackTrace();
        }

        mav.addObject("usuario", new Usuario());
        mav.addObject("departamentos", departamentos);
        mav.addObject("municipios", municipios);
        mav.addObject("roles", roles);
        mav.setViewName("registro");

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

        mav.setViewName("exito");

        return mav;
    }
    //*******************************Usuario*******************************



    //*******************************Listado EDITADO de Usuarios*******************************

    //*******************************Listado de Centros Escolares*******************************
    @RequestMapping("/listadoC")
    public ModelAndView listadoC(){
        ModelAndView mav = new ModelAndView();
        List<Municipio> municipios = null;
        List<CentroEscolar> centroEscolares = centroEscolarService.findAllAsc();

        try {
            municipios = municipioRepo.municipios();
        }catch(Exception e) {
            e.printStackTrace();
        }

        mav.addObject("municipios", municipios);
        mav.addObject("centroEscolares", centroEscolares);
        mav.setViewName("listaCentrosE");

        return mav;
    }

    //*******************************Listado EDITADO de Centros Escolares*******************************
    @RequestMapping("/editCentroE")
    public ModelAndView editC(@RequestParam(value = "codigoCF") String codigo, @RequestParam(value = "nombreCF") String nombre,
                              @RequestParam(value = "direccionCF") String direccion, @RequestParam(value = "telefonoCF") String telefono,
                              @RequestParam(value = "municipioCF") String municipio){
        ModelAndView mav = new ModelAndView();
        List<Municipio> municipios = null;
        List<CentroEscolar> centroEscolares = centroEscolarService.findAllAsc();

        try {
            municipios = municipioRepo.municipios();
        }catch(Exception e) {
            e.printStackTrace();
        }

        Integer ID = Integer.parseInt(codigo);

        CentroEscolar centroEscolar = centroEscolarService.findByID(ID);

        centroEscolar.setNombre(nombre);
        centroEscolar.setDireccion(direccion);
        centroEscolar.setTelefono(telefono);
        centroEscolar.setCodigoMunicipio(municipioService.findMunicipio(municipio).getCodigoMunicipio());

        centroEscolarService.insert(centroEscolar);

        mav.addObject("municipios", municipios);
        mav.addObject("centroEscolares", centroEscolares);
        mav.setViewName("listaCentrosE");

        return mav;
    }
    //*******************************Listado EDITADO de Centros Escolares*******************************

    //*******************************Registrar Centro Escolar*******************************
    @RequestMapping("/agregarC")
    public ModelAndView agregarC(){
        ModelAndView mav = new ModelAndView();

        List<Departamento> deparmento = null;
        List<Municipio> municipio = null;

        try{
            deparmento = departamentoRepo.findAll();
            municipio = municipioRepo.findAll();
        } catch(Exception e) {
            e.printStackTrace();
        }

        mav.addObject("departamentos", deparmento);
        mav.addObject("municipios", municipio);
        mav.addObject("centroEscolar", new CentroEscolar());
        mav.setViewName("agregarCentro");

        return mav;
    }
    //*******************************Registrar Centro Escolar*******************************

    //*******************************Validar registrar Centro Escolar*******************************
    @RequestMapping("/validarC")
    public ModelAndView validarC(@Valid @ModelAttribute CentroEscolar centroEscolar, BindingResult result){
        ModelAndView mav = new ModelAndView();
        List<Departamento> departamentos = null;
        List<Municipio> municipios = null;

        try {
            departamentos = departamentoRepo.findAll();
            municipios = municipioRepo.municipios();
        }catch(Exception e) {
            e.printStackTrace();
        }

        if(result.hasErrors()){
            mav.addObject("departamentos", departamentos);
            mav.addObject("municipios", municipios);
            mav.setViewName("agregarCentro");
        } else {
            try {
                centroEscolarService.insert(centroEscolar);
            }catch(Exception e) {
                e.printStackTrace();
            }
            List<CentroEscolar> centrosEscolares = centroEscolarService.findAllAsc();
            mav.addObject("centroEscolares", centrosEscolares);
            mav.setViewName("listaCentrosE");
        }

        return mav;
    }
    //*******************************Validar registrar Centro Escolar*******************************

    //*******************************Listado EDITADO de Materias*******************************
    @RequestMapping("/listaM")
    public ModelAndView listaM(){
        ModelAndView mav = new ModelAndView();
        List<Materia> materias = materiaService.findAllAsc();

        mav.addObject("materias", materias);
        mav.setViewName("listaMaterias");

        return mav;
    }
    //*******************************Editar Materia*******************************
    @RequestMapping("/editM")
    public ModelAndView editCM(@RequestParam(value = "codigoF") String codigo, @RequestParam(value = "materiaF") String nombre){
        ModelAndView mav = new ModelAndView();

        Integer ID = Integer.parseInt(codigo);

        Materia materia = materiaService.findByID(ID);

        materia.setMateria(nombre);

        materiaService.insert(materia);

        List<Materia> materias = materiaService.findAllAsc();

        mav.addObject("materias", materias);
        mav.setViewName("listaMaterias");

        return mav;
    }
    //*******************************Editar Materia*******************************
    @RequestMapping("/agregarM")
    public ModelAndView agregarM(){
        ModelAndView mav = new ModelAndView();

        mav.addObject("materia", new Materia());
        mav.setViewName("agregarMateria");

        return mav;
    }
    //*******************************Validar registro Materia*******************************
    @RequestMapping("/validarM")
    public ModelAndView validarM(@Valid @ModelAttribute Materia materia, BindingResult result){
        ModelAndView mav = new ModelAndView();

        if(result.hasErrors()){
            mav.setViewName("agregarMateria");
        } else {
            try {
                materiaService.insert(materia);
            }catch(Exception e) {
                e.printStackTrace();
            }
            List<Materia> materias = materiaService.findAllAsc();
            mav.addObject("materias", materias);
            mav.setViewName("listaMaterias");
        }

        return mav;
    }
}

