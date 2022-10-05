package com.example.ej7.crudvalidation.content.infrastructure.controller.profesor;

import com.example.ej7.crudvalidation.content.application.service.ProfesorService;
import com.example.ej7.crudvalidation.content.domain.CustomError;
import com.example.ej7.crudvalidation.content.domain.Profesor;
import com.example.ej7.crudvalidation.content.domain.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.content.infrastructure.controller.ControllerAux;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfesorControllerUpdate {

    @Autowired
    ProfesorService profesorService;

    Logger logger = LoggerFactory.logger(ProfesorControllerUpdate.class);

    ControllerAux aux = new ControllerAux();

    // Método para actualizar un profesor: UPDATE en CRUD
    @PutMapping("/profesor/{id}")
    public ResponseEntity<Object> editaProfesor(@PathVariable String id, @RequestBody Profesor profesor){
        try {
            logger.info("ACTUALIZACIÓN DE PROFESOR POR ID = "+id);
            aux.compruebaId(profesorService, id);
            Profesor p1 = profesorService.getById(id);
            logger.info("PROFESOR A ACTUALIZAR: "+p1.toString());
            if(profesor.getBranch()!=null){
                p1.setBranch(profesor.getBranch());
            }
            if(profesor.getPersona()!=null){
                p1.setPersona(profesor.getPersona());
            }
            if(profesor.getComments()!=null){
                p1.setComments(profesor.getComments());
            }
            profesorService.actualizaProfesor(p1);
            logger.info("PROFESOR ACTUALIZADO: "+p1.toString());
            return new ResponseEntity<>(p1, HttpStatus.OK);
        } catch (EntityNotFoundException e){
            String error2 = "PROFESOR NO ENCONTRADO.";
            logger.error(error2);
            logger.error(e.getMessage());
            return new ResponseEntity<>(new CustomError(404, error2),HttpStatus.NOT_FOUND);
        }
    }

}
