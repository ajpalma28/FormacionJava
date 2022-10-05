package com.example.ej7.crudvalidation.content.infrastructure.controller.persona;

import com.example.ej7.crudvalidation.content.application.service.PersonaService;
import com.example.ej7.crudvalidation.content.domain.CustomError;
import com.example.ej7.crudvalidation.content.domain.Persona;
import com.example.ej7.crudvalidation.content.domain.exceptions.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonaControllerGet {

    @Autowired
    PersonaService service;

    // Cargamos el logger
    Logger logger = LoggerFactory.getLogger(PersonaControllerEdit.class);

    PersonaControllerAux aux = new PersonaControllerAux();

    // Método para buscar una persona por ID: READ en CRUD
    @GetMapping("/persona/id/{id}")
    public ResponseEntity<Object> getPersonaId(@PathVariable int id){
        try {
            logger.info("BÚSQUEDA DE PERSONA POR ID = "+id);
            aux.compruebaId(service, id);
            Persona p1 = service.findById(id);
            logger.info("Persona encontrada: "+p1.toString());
            return new ResponseEntity<>(p1, HttpStatus.OK);
        } catch (EntityNotFoundException e){
            String error1 ="PERSONA NO ENCONTRADA.";
            logger.error(error1);
            logger.error(e.getMessage());
            return new ResponseEntity<>(new CustomError(404,error1),HttpStatus.NOT_FOUND);
        }

    }

    // Método para buscar una persona por nombre: READ en CRUD
    @GetMapping("/persona/usuario/{usuario}")
    public Persona getPersonaUsuario(@PathVariable String usuario){
        logger.info("BÚSQUEDA DE PERSONA POR USUARIO = "+usuario);
        Persona p1 = service.findByUsuario(usuario);
        if(p1==null){
            logger.error("PERSONA NO ENCONTRADA.");
        }else{
            logger.info("Persona encontrada: "+p1.toString());
        }
        return p1;
    }

    // Método para buscar todas las personas: READ en CRUD
    @GetMapping("/persona/all")
    public List<Persona> getListaPersonas(){
        List<Persona> lista = service.devuelveTodo();
        if(lista.isEmpty()){
            logger.error("NO hay personas registradas en la base de datos");
        }else{
            logger.info("PERSONAS REGISTRADAS: ");
            lista.forEach(x->logger.info(x.toString()));
        }
        return lista;
    }

}
