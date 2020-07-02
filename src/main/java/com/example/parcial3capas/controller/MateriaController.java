package com.example.parcial3capas.controller;

import com.example.parcial3capas.domain.Materia;
import com.example.parcial3capas.services.MateriaService;
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
public class MateriaController {

    @Autowired
    MateriaService materiaService;

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
