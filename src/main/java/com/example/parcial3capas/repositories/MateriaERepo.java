package com.example.parcial3capas.repositories;

import com.example.parcial3capas.domain.MateriaxEstudiante;
import com.example.parcial3capas.dto.EstudianteDTO;
import com.example.parcial3capas.dto.MateriaDTO;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MateriaERepo extends JpaRepository<MateriaxEstudiante, Integer> {

    @Query(nativeQuery=true, value="SELECT est.c_estudiante, est.s_nombre, est.s_apellido, CASE WHEN CAST(td.aprob AS decimal) IS NULL THEN 0 ELSE CAST(td.aprob AS decimal) END AS Aprobadas, CASE WHEN CAST(td.repro AS decimal) IS NULL THEN 0 ELSE CAST(td.repro AS decimal) END AS Reprobadas, CASE WHEN ROUND(AVG(CAST(ma.s_nota AS decimal)),2) IS NULL THEN 0 ELSE ROUND(AVG(CAST(ma.s_nota AS decimal)),2) END AS Promedio, est.c_centro  FROM (SELECT e.c_estudiante, CASE WHEN COUNT(CAST(e.s_nota AS decimal)) IS NULL THEN 0 ELSE COUNT(CAST(e.s_nota AS decimal)) END AS Aprob, CASE WHEN CAST(a.reprobadas AS decimal) IS NULL THEN 0 ELSE CAST(a.reprobadas AS decimal) END  AS Repro FROM (SELECT me.c_estudiante,  CASE WHEN COUNT(CAST(me.s_nota AS decimal)) IS NULL THEN 0 ELSE COUNT(CAST(me.s_nota AS decimal)) END AS Reprobadas FROM public.materia_estudiante me FULL OUTER JOIN public.estudiante e ON me.c_estudiante = e.c_estudiante WHERE CAST(me.s_nota AS decimal) < 6 GROUP BY me.c_estudiante ORDER BY me.c_estudiante ASC) AS a FULL OUTER JOIN public.materia_estudiante e ON a.c_estudiante = e.c_estudiante FULL OUTER JOIN public.estudiante es ON a.c_estudiante = es.c_estudiante WHERE CAST(e.s_nota AS decimal) >= 6 GROUP BY e.c_estudiante, a.reprobadas ORDER BY e.c_estudiante ASC) AS td FULL OUTER JOIN public.materia_estudiante ma ON td.c_estudiante = ma.c_estudiante FULL OUTER JOIN public.estudiante est ON ma.c_estudiante = est.c_estudiante GROUP BY est.c_estudiante, est.s_nombre, est.s_apellido, td.aprob, td.repro ORDER BY est.c_estudiante ASC")
    List<Object[]> materiasAprobadasyReprobadas() throws DataAccessException;

    @Query(nativeQuery=true, value="SELECT est.c_estudiante, est.s_nombre, est.s_apellido, CASE WHEN td.aprob IS NULL THEN 0 ELSE td.aprob END AS Aprobadas, CASE WHEN td.repro IS NULL THEN 0 ELSE td.repro END AS Reprobadas, CASE WHEN ROUND(AVG(CAST(ma.s_nota AS DECIMAL)),2) IS NULL THEN 0 ELSE ROUND(AVG(CAST(ma.s_nota AS DECIMAL)),2) END AS Promedio  FROM (SELECT e.c_estudiante, CASE WHEN COUNT(CAST(e.s_nota AS DECIMAL)) IS NULL THEN 0 ELSE COUNT(CAST(e.s_nota AS DECIMAL)) END AS Aprob, CASE WHEN a.reprobadas IS NULL THEN 0 ELSE a.reprobadas END  AS Repro FROM (SELECT me.c_estudiante,  CASE WHEN COUNT(CAST(me.s_nota AS DECIMAL)) IS NULL THEN 0 ELSE COUNT(CAST(me.s_nota AS DECIMAL)) END AS Reprobadas FROM public.materia_estudiante me FULL OUTER JOIN public.estudiante e ON me.c_estudiante = e.c_estudiante WHERE CAST(me.s_nota AS DECIMAL) < 6 GROUP BY me.c_estudiante ORDER BY me.c_estudiante ASC) AS a FULL OUTER JOIN public.materia_estudiante e ON a.c_estudiante = e.c_estudiante FULL OUTER JOIN public.estudiante es ON a.c_estudiante = es.c_estudiante WHERE CAST(e.s_nota AS DECIMAL) >= 6 GROUP BY e.c_estudiante, a.reprobadas ORDER BY e.c_estudiante ASC) AS td FULL OUTER JOIN public.materia_estudiante ma ON td.c_estudiante = ma.c_estudiante FULL OUTER JOIN public.estudiante est ON ma.c_estudiante = est.c_estudiante WHERE est.s_nombre = :nombre GROUP BY est.c_estudiante, est.s_nombre, est.s_apellido, td.aprob, td.repro ORDER BY est.c_estudiante ASC")
    List<Object[]> materiasNombre(String nombre) throws DataAccessException;

    @Query(nativeQuery=true, value="SELECT est.c_estudiante, est.s_nombre, est.s_apellido, CASE WHEN td.aprob IS NULL THEN 0 ELSE td.aprob END AS Aprobadas, CASE WHEN td.repro IS NULL THEN 0 ELSE td.repro END AS Reprobadas, CASE WHEN ROUND(AVG(CAST(ma.s_nota AS DECIMAL)),2) IS NULL THEN 0 ELSE ROUND(AVG(CAST(ma.s_nota AS DECIMAL)),2) END AS Promedio  FROM (SELECT e.c_estudiante, CASE WHEN COUNT(CAST(e.s_nota AS DECIMAL)) IS NULL THEN 0 ELSE COUNT(CAST(e.s_nota AS DECIMAL)) END AS Aprob, CASE WHEN a.reprobadas IS NULL THEN 0 ELSE a.reprobadas END  AS Repro FROM (SELECT me.c_estudiante,  CASE WHEN COUNT(CAST(me.s_nota AS DECIMAL)) IS NULL THEN 0 ELSE COUNT(CAST(me.s_nota AS DECIMAL)) END AS Reprobadas FROM public.materia_estudiante me FULL OUTER JOIN public.estudiante e ON me.c_estudiante = e.c_estudiante WHERE CAST(me.s_nota AS DECIMAL) < 6 GROUP BY me.c_estudiante ORDER BY me.c_estudiante ASC) AS a FULL OUTER JOIN public.materia_estudiante e ON a.c_estudiante = e.c_estudiante FULL OUTER JOIN public.estudiante es ON a.c_estudiante = es.c_estudiante WHERE CAST(e.s_nota AS DECIMAL) >= 6 GROUP BY e.c_estudiante, a.reprobadas ORDER BY e.c_estudiante ASC) AS td FULL OUTER JOIN public.materia_estudiante ma ON td.c_estudiante = ma.c_estudiante FULL OUTER JOIN public.estudiante est ON ma.c_estudiante = est.c_estudiante WHERE est.s_apellido = :apellido GROUP BY est.c_estudiante, est.s_nombre, est.s_apellido, td.aprob, td.repro ORDER BY est.c_estudiante ASC")
    List<Object[]> materiasApellido(String apellido) throws DataAccessException;

    @Query(nativeQuery=true, value="SELECT m.c_estudiante, e.s_nombre, e.s_apellido, COUNT(s_nota) AS Materias_Aprobadas" +
            "FROM public.materia_estudiante AS m" +
            "INNER JOIN public.estudiante AS e" +
            "ON m.c_estudiante = e.c_estudiante" +
            "WHERE m.s_nota > 6 AND e.s_nombre = :nombre AND e.s_nombre = :apellido" +
            "GROUP BY m.c_estudiante, e.s_nombre, e.s_apellido")
    List<MateriaxEstudiante> materiasNombreApellido(String nombre, String apellido) throws DataAccessException;

    @Query(nativeQuery=true, value="SELECT me.c_materia_estudiante, m.s_materia, me.s_anio, me.s_ciclo, me.s_nota, e.c_estudiante,e.s_nombre, CASE WHEN CAST(me.s_nota AS decimal) >= 6 THEN 'Aprobada' ELSE 'Reprobada' END AS Resultado FROM public.materia_estudiante me INNER JOIN public.materia m ON me.c_materia = m.c_materia INNER JOIN public.estudiante e ON me.c_estudiante = e.c_estudiante WHERE me.c_estudiante = :ID ORDER BY me.c_materia_estudiante ASC")
    List<Object[]> materiasE(Integer ID) throws DataAccessException;

    @Query(nativeQuery=true, value="SELECT me.c_materia_estudiante, m.s_materia, me.s_anio, me.s_ciclo, me.s_nota, e.c_estudiante,e.s_nombre, CASE WHEN CAST(me.s_nota AS decimal) >= 6 THEN 'Aprobada' ELSE 'Reprobada' END AS Resultado FROM public.materia_estudiante me INNER JOIN public.materia m ON me.c_materia = m.c_materia INNER JOIN public.estudiante e ON me.c_estudiante = e.c_estudiante WHERE me.c_materia_estudiante = :ID ORDER BY me.c_materia_estudiante ASC")
    List<Object[]> materiaByID(Integer ID) throws DataAccessException;
}
