package com.example.ej7.crudvalidation.content.infrastructure.rest.spring.dto;

import com.example.ej7.crudvalidation.content.domain.Persona;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorOutputDTO {

    long id_profesor;
    Persona persona;
    String comments;
    String branch;

}
