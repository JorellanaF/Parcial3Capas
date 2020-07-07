package com.example.parcial3capas.dto;

public class MateriaDTO {
    private String codigoME;
    private String materia;
    private String anio;
    private String ciclo;
    private String nota;
    private String codigoE;
    private String estudiante;
    private String resultado;

    public String getCodigoE() {
        return codigoE;
    }

    public void setCodigoE(String codigoE) {
        this.codigoE = codigoE;
    }

    public String getCodigoME() {
        return codigoME;
    }

    public void setCodigoME(String codigoME) {
        this.codigoME = codigoME;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
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

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
