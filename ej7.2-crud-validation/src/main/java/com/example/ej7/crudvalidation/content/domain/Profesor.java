package com.example.ej7.crudvalidation.content.domain;

import com.example.ej7.crudvalidation.Generador2;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "profesores")
@Getter
@Setter
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prof_seq")
    @GenericGenerator(
            name = "prof_seq",
            strategy = "com.example.ej7.crudvalidation.Generador2",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = Generador2.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = Generador2.VALUE_PREFIX_PARAMETER, value = "prof-"),
                    @org.hibernate.annotations.Parameter(name = Generador2.NUMBER_FORMAT_PARAMETER, value = "%04d")
            })
    String id_profesor;
    @OneToOne
    @JoinColumn(name = "id_persona")
    Persona persona;
    @Column(name = "comentarios")
    String comments;
    @NotNull
    @Column(name = "rama")
    String branch;

}
