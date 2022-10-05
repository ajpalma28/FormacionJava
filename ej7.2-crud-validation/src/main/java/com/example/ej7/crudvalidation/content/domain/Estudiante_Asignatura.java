package com.example.ej7.crudvalidation.content.domain;

import com.example.ej7.crudvalidation.Generador2;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "asignaturas")
@Getter
@Setter
public class Estudiante_Asignatura implements java.io.Serializable {

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
    private String id_asignatura;
    //@OneToMany
    //@JoinColumn(name = "id_student")
    //List<Student> id_student;
    @Column(name = "asignatura")
    private String asignatura;
    @Column(name = "comentarios")
    private String comments;
    @NotNull
    @Column(name = "fecha_inicial")
    private String initial_date;
    @Column(name = "fecha_final")
    private String finish_date;

}