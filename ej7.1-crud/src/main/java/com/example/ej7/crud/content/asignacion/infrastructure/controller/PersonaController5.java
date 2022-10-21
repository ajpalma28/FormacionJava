package com.example.ej7.crud.content.asignacion.infrastructure.controller;

import com.example.ej7.crud.content.asignacion.domain.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://cdpn.io", allowedHeaders = "*")
public class PersonaController5 {

    @Autowired
    List<Persona> listaPersonas;

    Logger logger = LoggerFactory.getLogger(PersonaController5.class);

    @PostMapping("/addperson")
    public ResponseEntity<Object> nuevaPersona(@RequestBody Persona persona){
        logger.info("Entro en addpersona");
        listaPersonas.add(persona);
        logger.info("NUEVA PERSONA: "+persona.toString());
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<Object> getTodos(){
        logger.info("MOSTRAMOS TODAS LAS PERSONAS");
        listaPersonas.forEach(x->logger.info(x.toString()));
        return new ResponseEntity<>(listaPersonas, HttpStatus.OK);
    }


}
