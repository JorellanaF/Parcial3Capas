package com.example.parcial3capas.controller;

import com.example.parcial3capas.domain.CentroEscolar;
import com.example.parcial3capas.domain.Departamento;
import com.example.parcial3capas.domain.Municipio;
import com.example.parcial3capas.repositories.DepartamentoRepo;
import com.example.parcial3capas.repositories.MunicipioRepo;
import com.example.parcial3capas.services.CentroEscolarService;
import com.example.parcial3capas.services.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CentroController {

    @Autowired
    private DepartamentoRepo departamentoRepo;

    @Autowired
    private MunicipioRepo municipioRepo;

    @Autowired
    MunicipioService municipioService;

    @Autowired
    CentroEscolarService centroEscolarService;

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
}
