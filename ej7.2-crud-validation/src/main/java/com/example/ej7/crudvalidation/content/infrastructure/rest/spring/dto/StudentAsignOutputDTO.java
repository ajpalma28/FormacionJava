package com.example.ej7.crudvalidation.content.infrastructure.rest.spring.dto;

import com.example.ej7.crudvalidation.content.domain.Estudiante_Asignatura;
import com.example.ej7.crudvalidation.content.domain.Persona;
import com.example.ej7.crudvalidation.content.domain.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StudentAsignOutputDTO {

    String id_student;
    String persona;
    Integer num_hours_week;
    String comments;
    String branch;
    List<Estudiante_AsignaturaOutputDTO> asignaturas;

    public StudentAsignOutputDTO(Student s){
        id_student = s.getId_student();
        persona = s.getPersona().toString();
        num_hours_week = s.getNum_hours_week();
        comments = s.getComments();
        branch = s.getBranch();
        asignaturas = new ArrayList<>();
        s.getAsignaturas().forEach(x->asignaturas.add(new Estudiante_AsignaturaOutputDTO(x)));
    }

}
