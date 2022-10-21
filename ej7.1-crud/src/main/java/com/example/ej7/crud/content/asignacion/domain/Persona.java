package com.example.ej7.crud.content.asignacion.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Persona implements java.io.Serializable {

    private String usuario;
    private String surname;
    private String name;
    private String password;
    private String company_email;
    private String personal_email;
    private String city;
    private String imagen_url;
    private String created_date;
    private Boolean active;
    private String termination_date;

    public String toString(){
        return "Nombre: "+name+" "+surname+"; Usuario: "+usuario+"; Ciudad: "+city+".";
    }
}
