package com.example.parcial3capas.services;

import com.example.parcial3capas.domain.Estudiante;
import com.example.parcial3capas.domain.MateriaxEstudiante;
import com.example.parcial3capas.domain.Usuario;
import com.example.parcial3capas.dto.EstudianteDTO;
import com.example.parcial3capas.dto.MateriaDTO;
import com.example.parcial3capas.repositories.CentroEscolarRepo;
import com.example.parcial3capas.repositories.EstudianteRepo;
import com.example.parcial3capas.repositories.MateriaERepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteServiceImpl implements EstudianteService{

    @Autowired
    EstudianteRepo estudianteRepo;

    @Autowired
    MateriaERepo materiaERepo;

    @Autowired
    CentroEscolarService centroEscolarService;

    @Override
    @Transactional
    public void insert(Estudiante estudiante) throws DataAccessException {
        String f_nacimiento = estudiante.getFechaNacimiento();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNac = LocalDate.parse(f_nacimiento, fmt);
        LocalDate hoy = LocalDate.now();
        Period periodo = Period.between(fechaNac, hoy);
        Integer edad = periodo.getYears();
        estudiante.setEdad(edad);

        System.out.println("++++++++++++++++++++++++++++++++++++ " + estudiante.getCodigoEstudiante());
        System.out.println("----------=-=-=-=-=-=-=-=-=-=-=-=-= "+estudiante.getCodigoCentro());
        estudiante.setCentroEscolar(centroEscolarService.findByID(estudiante.getCodigoCentro()));

        estudianteRepo.save(estudiante);


        //System.out.println(estudiante.getCodigoEstudiante());

        /*MateriaxEstudiante expe = new MateriaxEstudiante();
        expe.setAnio("yyyy-MM-dd");
        expe.setCiclo("00");
        expe.setNota(0);
        expe.setCodigoEstudiante(estudiante.getCodigoEstudiante());
        materiaERepo.save(expe);*/
    }

    @Override
    public List<Estudiante> findAllAsc() throws DataAccessException {
        return null;
    }

    @Override
    public List<EstudianteDTO> dtoEstudiante() throws DataAccessException{
        List<EstudianteDTO> estudiantes = materiaERepo.materiasAprobadasyReprobadas().stream().map(
                obj -> {
                    EstudianteDTO e = new EstudianteDTO();
                    e.setCodigoE(obj[0].toString());
                    e.setNombre(obj[1].toString());
                    e.setApellido(obj[2].toString());
                    e.setAprobadas(obj[3].toString());
                    e.setReprobadas(obj[4].toString());
                    e.setPromedio(obj[5].toString());
                    return e;
                }
        ).collect(Collectors.toList());
        return  estudiantes;
    }

    @Override
    public List<EstudianteDTO> dtoEstudianteByNombre(String nombre) throws DataAccessException {
        List<EstudianteDTO> estudiantes = materiaERepo.materiasNombre(nombre).stream().map(
                obj -> {
                    EstudianteDTO e = new EstudianteDTO();
                    e.setCodigoE(obj[0].toString());
                    e.setNombre(obj[1].toString());
                    e.setApellido(obj[2].toString());
                    e.setAprobadas(obj[3].toString());
                    e.setReprobadas(obj[4].toString());
                    e.setPromedio(obj[5].toString());
                    return e;
                }
        ).collect(Collectors.toList());
        return  estudiantes;
    }

    @Override
    public List<EstudianteDTO> dtoEstudianteByApellido(String apellido) throws DataAccessException {
        List<EstudianteDTO> estudiantes = materiaERepo.materiasApellido(apellido).stream().map(
                obj -> {
                    EstudianteDTO e = new EstudianteDTO();
                    e.setCodigoE(obj[0].toString());
                    e.setNombre(obj[1].toString());
                    e.setApellido(obj[2].toString());
                    e.setAprobadas(obj[3].toString());
                    e.setReprobadas(obj[4].toString());
                    e.setPromedio(obj[5].toString());
                    return e;
                }
        ).collect(Collectors.toList());
        return  estudiantes;
    }

    @Override
    public List<MateriaDTO> dtoMateriaByEstudiante(Integer ID) throws DataAccessException {
        List<MateriaDTO> materias = materiaERepo.materiasE(ID).stream().map(
                obj -> {
                    MateriaDTO e = new MateriaDTO();
                    e.setCodigoME(obj[0].toString());
                    e.setMateria(obj[1].toString());
                    e.setAnio(obj[2].toString());
                    e.setCiclo(obj[3].toString());
                    e.setNota(obj[4].toString());
                    e.setCodigoE(obj[5].toString());
                    e.setEstudiante(obj[6].toString());
                    e.setResultado(obj[7].toString());
                    return e;
                }
        ).collect(Collectors.toList());
        return  materias;
    }

    @Override
    public MateriaDTO dtoMateriaByCodigoMateria(Integer ID) throws DataAccessException {
        List<MateriaDTO> materias = materiaERepo.materiaByID(ID).stream().map(
                obj -> {
                    MateriaDTO e = new MateriaDTO();
                    e.setCodigoME(obj[0].toString());
                    e.setMateria(obj[1].toString());
                    e.setAnio(obj[2].toString());
                    e.setCiclo(obj[3].toString());
                    e.setNota(obj[4].toString());
                    e.setCodigoE(obj[5].toString());
                    e.setEstudiante(obj[6].toString());
                    e.setResultado(obj[7].toString());
                    return e;
                }
        ).collect(Collectors.toList());
        MateriaDTO mate = new MateriaDTO();
        mate.setCodigoME(materias.get(0).getCodigoME());
        mate.setMateria(materias.get(0).getMateria());
        mate.setAnio(materias.get(0).getAnio());
        mate.setCiclo(materias.get(0).getCiclo());
        mate.setNota(materias.get(0).getNota());
        mate.setCodigoE(materias.get(0).getCodigoE());
        mate.setEstudiante(materias.get(0).getEstudiante());
        mate.setResultado(materias.get(0).getResultado());
        return  mate;
    }

    @Override
    public Estudiante findByID(Integer ID) throws DataAccessException {
        return estudianteRepo.findByCodigoEstudiante(ID);
    }
}
