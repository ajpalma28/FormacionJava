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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asign_seq")
    @GenericGenerator(
            name = "asign_seq",
            strategy = "com.example.ej7.crudvalidation.Generador2",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = Generador2.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = Generador2.VALUE_PREFIX_PARAMETER, value = "asign-"),
                    @org.hibernate.annotations.Parameter(name = Generador2.NUMBER_FORMAT_PARAMETER, value = "%04d")
            })
    String id_asignatura;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_student")
    Student id_student;
    @Column(name = "asignatura")
    String asignatura;
    @Column(name = "comentarios")
    String comments;
    @NotNull
    @Column(name = "fecha_inicial")
    String initial_date;
    @Column(name = "fecha_final")
    String finish_date;

    public boolean equals(Object o){
        Estudiante_Asignatura ea = (Estudiante_Asignatura) o;
        return this.getId_asignatura().equals(ea.getId_asignatura());
    }

    public String toString(){
        return id_asignatura+": "+asignatura;
    }

}