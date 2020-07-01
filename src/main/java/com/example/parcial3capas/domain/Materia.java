package com.example.parcial3capas.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(schema = "public" , name = "materia")
public class Materia {

    @Id
    @Column(name = "c_materia")
    @GeneratedValue(generator = "materia_seq", strategy = GenerationType.AUTO)
    private Integer codigoMateria;

    @Size(message = "El campo sobrepasa la cantidad de 100 caracteres", max = 100)
    @NotEmpty
    @Column(name = "s_materia")
    private String materia;

    public Materia() {
    }

    public Integer getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(Integer codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }
}
