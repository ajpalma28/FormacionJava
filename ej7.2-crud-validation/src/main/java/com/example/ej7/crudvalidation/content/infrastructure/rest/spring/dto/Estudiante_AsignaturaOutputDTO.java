package com.example.ej7.crudvalidation.content.infrastructure.rest.spring.dto;

import com.example.ej7.crudvalidation.content.domain.Estudiante_Asignatura;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Estudiante_AsignaturaOutputDTO {

    String id_asignatura;
    String asignatura;
    String id_student;
    String comments;
    String initial_date;
    String finish_date;

    public Estudiante_AsignaturaOutputDTO(Estudiante_Asignatura ea){
        id_asignatura = ea.getId_asignatura();
        asignatura = ea.getAsignatura();
        if(ea.getId_student()!=null){
            id_student = ea.getId_student().getId_student();
        }
        comments = ea.getComments();
        initial_date = ea.getInitial_date();
        finish_date = ea.getFinish_date();
    }

}
