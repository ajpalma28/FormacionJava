package com.example.ej7.crud.content.asignacion.infrastructure.controller;

import com.example.ej7.crud.content.asignacion.domain.Persona;
import com.example.ej7.crud.content.asignacion.domain.PersonaImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonaController2 {

    @Autowired
    List<Persona> listaPersonas;

    Logger logger = LoggerFactory.getLogger(PersonaController2.class);

    @PutMapping("/persona/{id}")
    public Persona editaPersona(@PathVariable int id, @RequestBody PersonaImpl persona){
        Persona p = listaPersonas.get(id-1);
        logger.info("PERSONA QUE SE VA A MODIFICAR: "+p.toString());
        if(persona.getNombre() != null){
            p.setNombre(persona.getNombre());
        }
        if(persona.getEdad()!=null){
            p.setEdad(persona.getEdad());
        }
        if(persona.getPoblacion()!=null){
            p.setPoblacion(persona.getPoblacion());
        }
        logger.info("RESULTADO FINAL TRAS MODIFICACIÓN: "+p.toString());
        return p;
    }
}
