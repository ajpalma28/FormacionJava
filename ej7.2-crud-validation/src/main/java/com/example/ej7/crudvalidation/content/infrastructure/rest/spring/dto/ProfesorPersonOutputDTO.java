package com.example.ej7.crudvalidation.content.infrastructure.rest.spring.dto;

import com.example.ej7.crudvalidation.content.domain.Persona;
import com.example.ej7.crudvalidation.content.domain.Profesor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorPersonOutputDTO {

    private String id_profesor;
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

    public ProfesorPersonOutputDTO(Profesor s, Persona p){
        id_profesor = s.getId_profesor();
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

}
