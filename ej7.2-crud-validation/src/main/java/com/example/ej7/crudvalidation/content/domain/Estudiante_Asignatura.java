package com.example.ej7.crudvalidation.content.domain;

import com.example.ej7.crudvalidation.Generador2;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;


@Entity
@Table(name = "asignaturas")
@Getter
@Setter
public class Estudiante_Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asign-seq")
    @GenericGenerator(
            name = "asign-seq",
            strategy = "com.example.ej7.crudvalidation.Generador2",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = Generador2.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = Generador2.VALUE_PREFIX_PARAMETER, value = "asign-"),
                    @org.hibernate.annotations.Parameter(name = Generador2.NUMBER_FORMAT_PARAMETER, value = "%04d")
            })
    String id_asignatura;
    //@OneToMany
    //@JoinColumn(name = "id_student")
    //List<Student> id_student;
    @Column(name = "asignatura")
    String asignatura;
    @Column(name = "comentarios")
    String comments;
    @NotNull
    @Column(name = "fecha_inicial")
    String initial_date;
    @Column(name = "fecha_final")
    String finish_date;

}