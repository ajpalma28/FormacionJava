package com.example.ej7.crud.content.asignacion.infrastructure.controller;

import com.example.ej7.crud.content.asignacion.domain.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonaController2 {

    @Autowired
    List<Persona> listaPersonas;

    Logger logger = LoggerFactory.getLogger(PersonaController2.class);

    @PutMapping("/persona/{id}")
    public Persona editaPersona(@PathVariable int id, @RequestBody Persona persona){
        Persona p = listaPersonas.get(id-1);
        logger.info("PERSONA QUE SE VA A MODIFICAR: "+p.toString());
        if(persona.getName() != null){
            p.setName(persona.getName());
        }
        if(persona.getTermination_date()!=null){
            p.setTermination_date(persona.getTermination_date());
        }
        if(persona.getCity()!=null){
            p.setCity(persona.getCity());
        }
        logger.info("RESULTADO FINAL TRAS MODIFICACIÓN: "+p.toString());
        return p;
    }
}
