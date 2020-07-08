package com.example.parcial3capas.dto;

public class EstudianteDTO {

    private String codigoE;
    private String nombre;
    private String apellido;
    private String aprobadas;
    private String reprobadas;
    private String promedio;
    private String codigoC;

    public String getCodigoC() {
        return codigoC;
    }

    public void setCodigoC(String codigoC) {
        this.codigoC = codigoC;
    }

    public String getCodigoE() {
        return codigoE;
    }

    public void setCodigoE(String codigoE) {
        this.codigoE = codigoE;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getAprobadas() {
        return aprobadas;
    }

    public void setAprobadas(String aprobadas) {
        this.aprobadas = aprobadas;
    }

    public String getReprobadas() {
        return reprobadas;
    }

    public void setReprobadas(String reprobadas) {
        this.reprobadas = reprobadas;
    }

    public String getPromedio() {
        return promedio;
    }

    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }
}
