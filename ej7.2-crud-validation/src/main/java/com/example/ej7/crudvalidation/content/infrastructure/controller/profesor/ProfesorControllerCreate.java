package com.example.ej7.crudvalidation.content.infrastructure.controller.profesor;

import com.example.ej7.crudvalidation.content.application.service.PersonaService;
import com.example.ej7.crudvalidation.content.application.service.ProfesorService;
import com.example.ej7.crudvalidation.content.application.service.StudentService;
import com.example.ej7.crudvalidation.content.domain.CustomError;
import com.example.ej7.crudvalidation.content.domain.Profesor;
import com.example.ej7.crudvalidation.content.domain.exceptions.RolPersonException;
import com.example.ej7.crudvalidation.content.domain.exceptions.UnprocessableEntityException;
import com.example.ej7.crudvalidation.content.infrastructure.controller.ControllerAux;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfesorControllerCreate {

    @Autowired
    ProfesorService profesorService;
    @Autowired
    StudentService studentService;
    @Autowired
    PersonaService personaService;

    // Cargamos el logger
    Logger logger = LoggerFactory.getLogger(ProfesorControllerCreate.class);

    ControllerAux aux = new ControllerAux();

    // Método para añadir nueva persona: CREATE en CRUD
    @PostMapping("/profesor")
    public ResponseEntity<Object> addProfesor(@RequestBody Profesor profesor){
        try {
            aux.compruebaBranch(profesor.getBranch());
            aux.compruebaRolCorrecto(studentService, profesor.getPersona().getId());
            profesor.setPersona(personaService.findById(profesor.getPersona().getId()));
            profesorService.addProfesor(profesor);
            logger.info("NUEVO PROFESOR AÑADIDO: " + profesor.toString());
            return new ResponseEntity<>(profesor, HttpStatus.OK);
        } catch (RolPersonException rpe) {
            logger.error(rpe.getMessage());
            return new ResponseEntity<>(new CustomError(409, rpe.getMessage()),HttpStatus.CONFLICT);
        } catch (UnprocessableEntityException e1) {
            String errorr = "FORMATO DE CAMPO INCORRECTO.";
            logger.error(errorr);
            logger.error(e1.getMessage());
            return new ResponseEntity<>(new CustomError(422,errorr),HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity<>(new CustomError(400, e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
