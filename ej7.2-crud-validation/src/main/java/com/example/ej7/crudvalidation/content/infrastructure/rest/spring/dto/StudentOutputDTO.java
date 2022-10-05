package com.example.ej7.crudvalidation.content.infrastructure.rest.spring.dto;

import com.example.ej7.crudvalidation.content.domain.Persona;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentOutputDTO {

    String id_student;
    Persona persona;
    Integer num_hours_week;
    String comments;
    String branch;

}
