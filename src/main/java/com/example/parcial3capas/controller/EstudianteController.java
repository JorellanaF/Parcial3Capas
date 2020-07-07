package com.example.parcial3capas.controller;

import com.example.parcial3capas.domain.*;
import com.example.parcial3capas.dto.EstudianteDTO;
import com.example.parcial3capas.dto.MateriaDTO;
import com.example.parcial3capas.repositories.*;
import com.example.parcial3capas.services.CentroEscolarService;
import com.example.parcial3capas.services.EstudianteService;
import com.example.parcial3capas.services.MateriaEService;
import com.example.parcial3capas.services.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.List;

@Controller
public class EstudianteController {

    @Autowired
    DepartamentoRepo departamentoRepo;

    @Autowired
    MunicipioRepo municipioRepo;

    @Autowired
    CentroEscolarService centroEscolarService;

    @Autowired
    EstudianteService estudianteService;

    @Autowired
    MateriaERepo materiaERepo;

    @Autowired
    MateriaRepo materiaRepo;

    @RequestMapping("/registroE")
    public ModelAndView registroE(){
        ModelAndView mav = new ModelAndView();
        List<Departamento> departamentos = null;
        List<Municipio> municipios = null;
        List<CentroEscolar> centros = null;

        try {
            departamentos = departamentoRepo.findAll();
            municipios = municipioRepo.municipios();
            centros = centroEscolarService.findAll();
            mav.addObject("estudiante", new Estudiante());
            mav.addObject("departamentos", departamentos);
            mav.addObject("municipios", municipios);
            mav.addObject("centros", centros);
            mav.setViewName("registroE");
        }catch(Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    //Validando el formularo de Registro
    @RequestMapping("/validarEs")
    public ModelAndView validarR(@Valid @ModelAttribute Estudiante estudiante, BindingResult result){
        ModelAndView mav = new ModelAndView();
        List<Departamento> departamentos = null;
        List<Municipio> municipios = null;
        List<CentroEscolar> centros = null;

        if(result.hasErrors()){
            departamentos = departamentoRepo.findAll();
            municipios = municipioRepo.municipios();
            centros = centroEscolarService.findAll();
            mav.addObject("departamentos", departamentos);
            mav.addObject("municipios", municipios);
            mav.addObject("centros", centros);
            mav.setViewName("registroE");
        }else {
            estudianteService.insert(estudiante);
            List<EstudianteDTO> materiaxEstudiantes = estudianteService.dtoEstudiante();
            mav.addObject("materiaxEstudiantes", materiaxEstudiantes);
            mav.setViewName("listaEstudiantes");
        }

        return mav;
    }

    //*******************************Editar de Estudiante*******************************
    @RequestMapping("/editarEs")
    public ModelAndView editarEs(@RequestParam(value = "codigoE") String codigo){
        ModelAndView mav = new ModelAndView();
        List<Departamento> departamentos = null;
        List<Municipio> municipios = null;
        List<CentroEscolar> centros = null;
        Estudiante estudiante = estudianteService.findByID(Integer.parseInt(codigo));

        try {
            departamentos = departamentoRepo.findAll();
            municipios = municipioRepo.municipios();
            centros = centroEscolarService.findAll();
            mav.addObject("estudiante", estudiante);
            mav.addObject("departamentos", departamentos);
            mav.addObject("municipios", municipios);
            mav.addObject("centros", centros);

            mav.addObject("fecha", estudiante.getFechaNacimiento());
            mav.addObject("depart", estudiante.getCentroEscolar().getMunicipio().getDepartamento().getCodigoDepartamento());
            mav.addObject("muni", estudiante.getCentroEscolar().getMunicipio().getCodigoMunicipio());
            mav.addObject("codigo", codigo);

            mav.setViewName("editarE");
        }catch(Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    //*******************************Listado de Estudiante*******************************
    @RequestMapping("/listadoE")
    public ModelAndView listadoE(){
        ModelAndView mav = new ModelAndView();
        List<EstudianteDTO> materiaxEstudiantes = estudianteService.dtoEstudiante();

        mav.addObject("materiaxEstudiantes", materiaxEstudiantes);
        mav.setViewName("listaEstudiantes");

        return mav;
    }

    //*******************************Listado de Estudiante*******************************
    @RequestMapping("/buscarE")
    public ModelAndView buscarE(@RequestParam(value = "criterio") String criterio, @RequestParam(value = "buscado") String buscado){
        ModelAndView mav = new ModelAndView();

        Integer opcion = Integer.parseInt(criterio);

        if(opcion == 0){
            List<EstudianteDTO> materiaxEstudiantes = estudianteService.dtoEstudiante();
            mav.addObject("materiaxEstudiantes", materiaxEstudiantes);
            mav.setViewName("listaEstudiantes");
        } else {
            if (opcion == 1){
                List<EstudianteDTO> materiaxEstudiantes = estudianteService.dtoEstudianteByNombre(buscado);
                mav.addObject("materiaxEstudiantes", materiaxEstudiantes);
                mav.setViewName("listaEstudiantes");
            } if (opcion == 2) {
                List<EstudianteDTO> materiaxEstudiantes = estudianteService.dtoEstudianteByApellido(buscado);
                mav.addObject("materiaxEstudiantes", materiaxEstudiantes);
                mav.setViewName("listaEstudiantes");
            }
        }

        return mav;
    }


    @RequestMapping("/listadoME")
    public ModelAndView listadoME(@RequestParam(value = "codigoME") String codigo){
        ModelAndView mav = new ModelAndView();

        List<Materia> materias = null;
        List<MateriaDTO> materiasE = null;

        try {
            materiasE = estudianteService.dtoMateriaByEstudiante(Integer.parseInt(codigo));
            materias = materiaRepo.findAllByOrderByCodigoMateriaAsc();
            if (materiasE.size() == 0){
                mav.addObject("materias", materias);
                mav.addObject("materia", new MateriaxEstudiante());
                mav.addObject("codigo", codigo);
                mav.setViewName("agregarMateriaE");
            } else {
                mav.addObject("materias", materias);
                mav.addObject("materiasE", materiasE);
                mav.addObject("titulo","Materias Cursadas de " + materiasE.get(0).getEstudiante());
                mav.setViewName("listaMateriasE");
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

        return  mav;
    }

    @Autowired
    MateriaEService materiaEService;

    @RequestMapping("/validarME")
    public  ModelAndView validarME(@RequestParam(value = "codigoE") String codigo, @ModelAttribute MateriaxEstudiante materia, BindingResult result){
        ModelAndView mav = new ModelAndView();
        List<Materia> materias = null;

        if (result.hasErrors()){
            mav.setViewName("agregarMateriaE");
        } else {
            try {
                //materias = materiaRepo.findAllByOrderByCodigoMateriaAsc();
                System.out.println("fechasaasdsda " + materia.getCodigoMateria());
                materia.setEstudiante(estudianteService.findByID(Integer.parseInt(codigo)));
                materiaEService.insert(materia);
                List<EstudianteDTO> materiaxEstudiantes = estudianteService.dtoEstudiante();
                mav.addObject("materiaxEstudiantes", materiaxEstudiantes);
                mav.setViewName("listaEstudiantes");
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return  mav;
    }

    @RequestMapping("/editME")
    public  ModelAndView editME(@RequestParam(value = "codigoME") String codigo, @RequestParam(value = "estudiante") String estudiante,
                                @RequestParam(value = "materia") String materiaS, @ModelAttribute MateriaxEstudiante materia, BindingResult result){
        ModelAndView mav = new ModelAndView();
        List<Materia> materias = null;

        System.out.println("aaaaaaaaaaaaa"+materiaS);
        System.out.println("eeeeeeeeeeeee"+estudiante);

        try {
            materias = materiaRepo.findAllByOrderByCodigoMateriaAsc();
            mav.addObject("materias", materias);
            MateriaDTO materiaE = estudianteService.dtoMateriaByCodigoMateria(Integer.parseInt(codigo));
            mav.addObject("titulo", "Editanto " + materiaS + " a " + estudiante);
            mav.addObject("materiaE", materiaE);
            mav.addObject("estudiante", estudiante);
            mav.addObject("codigoME", codigo);
            mav.setViewName("editarME");
        } catch(Exception e) {
            e.printStackTrace();
        }


        return  mav;
    }

    @Autowired
    EstudianteRepo estudianteRepo;

    @RequestMapping("/validarEditME")
    public  ModelAndView validarEditME(@RequestParam(value = "estudiante") String estudiante, @RequestParam(value = "codigoME") String codigo,
                                       @Valid @ModelAttribute MateriaDTO materia, BindingResult result){
        ModelAndView mav = new ModelAndView();

        if (result.hasErrors()){
            mav.setViewName("editarME");
        } else{
            MateriaxEstudiante materiaE = new MateriaxEstudiante();
            materiaE.setCodigoMateriaE(Integer.parseInt(codigo));
            materiaE.setEstudiante(estudianteRepo.findByCodigoEstudiante(Integer.parseInt(estudiante)));
            materiaE.setMateria(materiaRepo.findByCodigoC(Integer.parseInt(materia.getMateria())));
            materiaE.setNota(materia.getNota());
            materiaE.setAnio(materia.getAnio());
            materiaE.setCiclo(materia.getCiclo());
            materiaEService.insert(materiaE);
            List<EstudianteDTO> materiaxEstudiantes = estudianteService.dtoEstudiante();
            mav.addObject("materiaxEstudiantes", materiaxEstudiantes);
            mav.setViewName("listaEstudiantes");
        }

        return  mav;
    }

}
