package com.example.ej6.simplecontrollers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Useradd {

    @PostMapping(value = "/useradd")
    public Person creaPersona(@RequestBody Persona persona){
        return new PersonImpl(persona.nombre, Integer.parseInt(persona.edad), persona.ciudad);
    }

}
