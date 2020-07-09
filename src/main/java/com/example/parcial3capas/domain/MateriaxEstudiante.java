package com.example.parcial3capas.domain;

import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(schema = "public", name = "materia_estudiante")
public class MateriaxEstudiante {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_materia")
    private Materia materia;

    @Transient
    private Integer codigoMateria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_estudiante")
    private Estudiante estudiante;

    @Transient
    private Integer codigoEstudiante;

    @Id
    @GeneratedValue(generator = "materia_estudiante_seq", strategy = GenerationType.AUTO)
    @Column(name = "c_materia_estudiante")
    private Integer codigoMateriaE;

    @Column(name = "s_anio")
    private String anio;

    @Column(name = "s_ciclo")
    private String ciclo;

    @Column(name = "s_nota")
    private String nota;

    public MateriaxEstudiante() {
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Integer getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(Integer codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Integer getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(Integer codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public Integer getCodigoMateriaE() {
        return codigoMateriaE;
    }

    public void setCodigoMateriaE(Integer codigoMateriaE) {
        this.codigoMateriaE = codigoMateriaE;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
