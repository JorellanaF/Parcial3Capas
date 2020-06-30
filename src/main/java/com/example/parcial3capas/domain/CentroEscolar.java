package com.example.parcial3capas.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(schema = "public", name = "centro_escolar")
public class CentroEscolar {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_municipio")
    private Municipio municipio ;

    @Transient
    private Integer codigoMunicipio;

    @Id
    @GeneratedValue(generator = "centros_escolares_seq", strategy = GenerationType.AUTO)
    @Column(name = "c_centro_escolar")
    private Integer codigoCentro;

    @NotEmpty(message = "El campo no debe estar vacio")
    @Size(message = "El campo no debe tener mas de 100 caracteres", max = 100)
    @Column(name = "s_nombre")
    private String nombre;

    @NotEmpty(message = "El campo no debe estar vacio")
    @Size(message = "El campo no debe tener mas de 150 caracteres", max = 150)
    @Column(name = "s_direccion")
    private String direccion;

    @NotEmpty(message = "El campo no debe estar vacio")
    @Size(message = "El campo no debe tener mas de 12 caracteres", max = 12)
    @Column(name = "s_telefono")
    private String telefono;

    public CentroEscolar() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Integer getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Integer codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public Integer getCodigoCentro() {
        return codigoCentro;
    }

    public void setCodigoCentro(Integer codigoCentro) {
        this.codigoCentro = codigoCentro;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
