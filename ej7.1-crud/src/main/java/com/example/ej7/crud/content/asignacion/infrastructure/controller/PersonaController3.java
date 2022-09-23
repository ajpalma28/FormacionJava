package com.example.ej7.crud.content.asignacion.infrastructure.controller;

import com.example.ej7.crud.content.asignacion.domain.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonaController3 {

    @Autowired
    List<Persona> listaPersonas;

    Logger logger = LoggerFactory.getLogger(PersonaController3.class);

    @DeleteMapping("/persona/{id}")
    public void borraPersona(@PathVariable int id){
        if(id<=listaPersonas.size()){
            logger.info("PERSONA A ELIMINAR: "+listaPersonas.get(id-1));
            logger.info("PERSONAS REGISTRADAS ACTUALMENTE ---------->");
            listaPersonas.forEach(x->logger.info(x.toString()));
            listaPersonas.remove(id-1);
            logger.info("¡BORRADO REALIZADO! Lista resultante ---------->");
            listaPersonas.forEach(x->logger.info(x.toString()));
        }else{
            logger.info("ERROR: No hay ningún usuario con identificador "+id);
        }
    }
}
