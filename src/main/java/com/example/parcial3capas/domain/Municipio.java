package com.example.parcial3capas.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(schema = "public", name = "municipio")
public class Municipio {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_departamento")
    private Departamento departamento;

    @Transient
    private Integer codigoDepartamento;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "c_municipio")
    private Integer codigoMunicipio;

    @Column(name = "s_municipio")
    @NotEmpty
    private String municipio;

    public Municipio() {
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

    public Integer getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Integer codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
}
