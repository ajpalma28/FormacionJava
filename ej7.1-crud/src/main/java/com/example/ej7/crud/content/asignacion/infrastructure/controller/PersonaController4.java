package com.example.ej7.crud.content.asignacion.infrastructure.controller;

import com.example.ej7.crud.content.asignacion.domain.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonaController4 {

    @Autowired
    List<Persona> listaPersonas;

    Logger logger = LoggerFactory.getLogger(PersonaController4.class);

    @GetMapping("/persona/{id}")
    public Persona getPersona(@PathVariable int id){
        logger.info("BUSCAMOS PERSONA POR ID: "+id);
        if(listaPersonas.size()>=id){
            logger.info("PERSONA ENCONTRADA: "+listaPersonas.get(id-1));
            return listaPersonas.get(id-1);
        }else{
            logger.error("PERSONA NO ENCONTRADA");
            return null;
        }
    }

    @GetMapping("/persona/nombre/{nombre}")
    public List<Persona> getPersonaNombre(@PathVariable String nombre){
        List<Persona> res = new ArrayList<Persona>();
        logger.info("Vamos a buscar las personas cuyo nombre sea "+nombre);
        listaPersonas.stream().filter(x->x.getName().equals(nombre)).forEach(res::add);
        if(!res.isEmpty()){
            logger.info("PERSONAS ENCONTRADAS -------->");
            res.forEach(x->logger.info(x.toString()));
        }else{
            logger.error("ERROR: No se han encontrado personas llamadas "+nombre);
        }
        return res;
    }

}
