package com.example.ej7.crud.content.asignacion.infrastructure.controller;

import com.example.ej7.crud.content.asignacion.domain.Persona;
import com.example.ej7.crud.content.asignacion.domain.PersonaImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonaController1 {

    @Autowired
    List<Persona> listaPersonas;

    Logger logger = LoggerFactory.getLogger(PersonaController1.class);

    @PostMapping("/persona")
    public Persona addPersona(@RequestBody PersonaImpl persona){
        listaPersonas.add(persona);
        logger.info("NUEVA PERSONA AÑADIDA: "+persona.toString());
        return persona;
    }
}
