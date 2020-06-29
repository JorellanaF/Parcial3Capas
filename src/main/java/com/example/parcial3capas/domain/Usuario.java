package com.example.parcial3capas.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@Entity
@Table(schema = "public", name = "usuario")
public class Usuario {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_departamento")
    private Departamento departamento;

    @Transient
    @Column(name = "c_departamento")
    private Integer codigoDepartamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_rol")
    private Rol rol;

    @Transient
    @Column(name = "c_rol")
    private Integer codigoRol;

    @Id
    @GeneratedValue(generator = "usuario_seq", strategy = GenerationType.AUTO)
    @Column(name = "c_usuario")
    private Integer codigoUsuario;

    @Size(message = "El campo sobrepasa la cantidad de 50 caracteres", max = 50)
    @Column(name = "nombre")
    @NotEmpty(message = "El campo no debe estar vacio")
    private String nombre;

    @Size(message = "El campo sobrepasa la cantidad de 50 caracteres", max = 50)
    @Column(name = "apellido")
    @NotEmpty(message = "El campo no debe estar vacio")
    private String apellido;

    @Column(name = "f_nacimiento")
    private String fechaNacimiento;

    @Column(name = "edad")
    private Integer edad;

    @Size(message = "El campo sobrepasa la cantidad de 150 caracteres", max = 150)
    @Column(name = "direccion")
    @NotEmpty(message = "El campo no debe estar vacio")
    private String direccionResidencia;

    @NotEmpty
    @Column(name = "estado")
    private String estado;

    @Size(message = "El campo sobrepasa la cantidad de 50 caracteres", max = 50)
    @Column(name = "usuario")
    @NotEmpty(message = "El campo no debe estar vacio")
    private String usuario;

    @Size(message = "El campo sobrepasa la cantidad de 150 caracteres", max = 150)
    @Column(name = "contraseña")
    @NotEmpty(message = "El campo no debe estar vacio")
    private String contraseña;

    public Usuario() {
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Integer getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(Integer codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Integer getCodigoRol() {
        return codigoRol;
    }

    public void setCodigoRol(Integer codigoRol) {
        this.codigoRol = codigoRol;
    }

    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Integer codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }
}
