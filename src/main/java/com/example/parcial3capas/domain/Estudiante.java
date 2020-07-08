package com.example.parcial3capas.domain;

import org.springframework.stereotype.Controller;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(schema = "public", name = "estudiante")
public class Estudiante {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_centro")
    private CentroEscolar centroEscolar;

    @Transient
    @Column(name = "c_centro")
    private Integer codigoCentro;

    @Id
    @GeneratedValue(generator = "estudiante_seq", strategy = GenerationType.AUTO)
    @Column(name = "c_estudiante")
    private Integer codigoEstudiante;

    @Size(message = "El campo sobrepasa la cantidad de 100 caracteres", max = 100)
    @NotEmpty(message = "El campo no debe estar vacio")
    @Column(name = "s_nombre")
    private String nombre;

    @Size(message = "El campo sobrepasa la cantidad de 100 caracteres", max = 100)
    @NotEmpty(message = "El campo no debe estar vacio")
    @Column(name = "s_apellido")
    private String apellido;

    @Size(message = "El campo debe tener 9 caracteres", min = 9)
    @Size(message = "El campo sobrepasa la cantidad de 9 caracteres", max = 9)
    @NotEmpty(message = "El campo no debe estar vacio")
    @Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "El campo debe ser Alfanumerico")
    @Column(name = "s_carnet")
    private String carnet;

    @Column(name = "f_nacimiento")
    @NotEmpty(message = "El campo no debe estar vacio")
    private String fechaNacimiento;

    @Column(name = "s_edad")
    private Integer edad;

    @Size(message = "El campo sobrepasa la cantidad de 150 caracteres", max = 150)
    @Column(name = "s_direccion")
    @NotEmpty(message = "El campo no debe estar vacio")
    private String direccionResidencia;

    @Size(message = "El campo sobrepasa la cantidad de 12 caracteres", max = 12)
    @Column(name = "s_telefono")
    @NotEmpty(message = "El campo no debe estar vacio")
    private String telefono;

    @Size(message = "El campo sobrepasa la cantidad de 12 caracteres", max = 12)
    @Column(name = "s_celular")
    @NotEmpty(message = "El campo no debe estar vacio")
    private String celular;

    @Size(message = "El campo sobrepasa la cantidad de 150 caracteres", max = 150)
    @Column(name = "s_nombre_padre")
    @NotEmpty(message = "El campo no debe estar vacio")
    private String nombrePadre;

    @Size(message = "El campo sobrepasa la cantidad de 150 caracteres", max = 150)
    @Column(name = "s_nombre_madre")
    @NotEmpty(message = "El campo no debe estar vacio")
    private String nombreMadre;

    public Estudiante() {
    }

    public CentroEscolar getCentroEscolar() {
        return centroEscolar;
    }

    public void setCentroEscolar(CentroEscolar centroEscolar) {
        this.centroEscolar = centroEscolar;
    }

    public Integer getCodigoCentro() {
        return codigoCentro;
    }

    public void setCodigoCentro(Integer codigoCentro) {
        this.codigoCentro = codigoCentro;
    }

    public Integer getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(Integer codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
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

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getNombrePadre() {
        return nombrePadre;
    }

    public void setNombrePadre(String nombrePadre) {
        this.nombrePadre = nombrePadre;
    }

    public String getNombreMadre() {
        return nombreMadre;
    }

    public void setNombreMadre(String nombreMadre) {
        this.nombreMadre = nombreMadre;
    }
}
