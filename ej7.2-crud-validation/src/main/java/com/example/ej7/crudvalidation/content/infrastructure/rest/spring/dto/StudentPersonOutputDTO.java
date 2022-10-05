package com.example.ej7.crudvalidation.content.infrastructure.rest.spring.dto;

import com.example.ej7.crudvalidation.content.domain.Persona;
import com.example.ej7.crudvalidation.content.domain.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class StudentPersonOutputDTO {

    private String id_student;
    private int num_hours_week;
    private String comments;
    private String id_persona;
    private String user;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Boolean active;
    private String created_date;
    private String imagen_url;
    private String termination_date;

    public StudentPersonOutputDTO(Student s, Persona p){
        id_student = s.getId_student();
        num_hours_week = s.getNum_hours_week();
        comments = s.getComments();
        //id_persona = s.getPersona().getId();
        user = p.getUsuario();
        password = p.getPassword();
        name = p.getName();
        surname = p.getSurname();
        company_email = p.getCompany_email();
        personal_email = p.getPersonal_email();
        city = p.getCity();
        active = p.getActive();
        created_date = p.getCreated_date();
        imagen_url = p.getImagen_url();
        termination_date = p.getTermination_date();
    }

    public String toString(){
        return id_student + " - " + user + ": " + company_email;
    }
}
