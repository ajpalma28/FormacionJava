package com.example.ej6.simplecontrollers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Useradd {

    Logger logger = LoggerFactory.getLogger(Useradd.class);

    @PostMapping(value = "/useradd", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Person creaPersona(@RequestBody Persona persona){
        Person p = new PersonImpl(persona.nombre, Integer.parseInt(persona.edad), persona.ciudad);
        logger.info("NUEVO USUARIO -> "+p.toString());
        return p;
    }

}
