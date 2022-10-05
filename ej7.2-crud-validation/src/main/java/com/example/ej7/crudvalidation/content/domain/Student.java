package com.example.ej7.crudvalidation.content.domain;

import com.example.ej7.crudvalidation.Generador2;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "estudiantes")
@Getter
@Setter
public class Student implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @GenericGenerator(
            name = "student_seq",
            strategy = "com.example.ej7.crudvalidation.Generador2",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = Generador2.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = Generador2.VALUE_PREFIX_PARAMETER, value = "stud-"),
                    @org.hibernate.annotations.Parameter(name = Generador2.NUMBER_FORMAT_PARAMETER, value = "%04d")
            })
    String id_student;
    @OneToOne
    @JoinColumn(name = "id_persona")
    Persona persona;
    @NotNull
    @Column(name = "horas_por_semana")
    Integer num_hours_week;
    @Column(name = "comentarios")
    String comments;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    Profesor profesor;
    @NotNull
    @Column(name = "rama")
    String branch;
    //@OneToMany
    //List<Estudiante_Asignatura> asignaturas;

}
