package com.example.ej7.crudvalidation.content.infrastructure.controller.profesor;

import com.example.ej7.crudvalidation.content.application.service.ProfesorRepository;
import com.example.ej7.crudvalidation.content.application.service.ProfesorService;
import com.example.ej7.crudvalidation.content.application.service.StudentService;
import com.example.ej7.crudvalidation.content.domain.CustomError;
import com.example.ej7.crudvalidation.content.domain.Profesor;
import com.example.ej7.crudvalidation.content.domain.Student;
import com.example.ej7.crudvalidation.content.domain.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.content.infrastructure.controller.ControllerAux;
import com.example.ej7.crudvalidation.content.infrastructure.controller.persona.PersonaControllerEdit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfesorControllerDelete {

    @Autowired
    ProfesorService service;

    // Cargamos el logger
    Logger logger = LoggerFactory.getLogger(ProfesorControllerDelete.class);

    ControllerAux aux = new ControllerAux();

    // Método para eliminar profesor por ID: DELETE en CRUD
    @DeleteMapping("/profesor/{id}")
    public ResponseEntity<Object> borraProfesor(@PathVariable String id){
        try{
            aux.compruebaId(service,id);
            Profesor p1 = service.getById(id);
            logger.info("SE BORRA EL PROFESOR: "+p1.toString());
            service.eliminaProfesor(p1);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            logger.error("Profesor con ID "+id+" no borrado. RAZÓN:");
            logger.error(e.getMessage());
            return new ResponseEntity<>(new CustomError(400, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

}
