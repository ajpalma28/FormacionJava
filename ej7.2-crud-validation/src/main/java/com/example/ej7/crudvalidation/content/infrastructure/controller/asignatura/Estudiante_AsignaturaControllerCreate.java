package com.example.ej7.crudvalidation.content.infrastructure.controller.asignatura;

import com.example.ej7.crudvalidation.content.application.service.Estudiante_AsignaturaService;
import com.example.ej7.crudvalidation.content.domain.CustomError;
import com.example.ej7.crudvalidation.content.domain.Estudiante_Asignatura;
import com.example.ej7.crudvalidation.content.domain.exceptions.UnprocessableEntityException;
import com.example.ej7.crudvalidation.content.infrastructure.controller.ControllerAux;
import com.example.ej7.crudvalidation.content.infrastructure.controller.persona.PersonaControllerEdit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Estudiante_AsignaturaControllerCreate {

    @Autowired
    Estudiante_AsignaturaService service;

    // Cargamos el logger
    Logger logger = LoggerFactory.getLogger(Estudiante_AsignaturaControllerCreate.class);

    ControllerAux aux = new ControllerAux();

    // Método para añadir nueva persona: CREATE en CRUD
    @PostMapping("/asignatura")
    public ResponseEntity<Object> addAsignatura(@RequestBody Estudiante_Asignatura asignatura){
        try {
            aux.compruebaFecha(asignatura.getInitial_date());
            service.addAsignatura(asignatura);
            logger.info("NUEVA ASIGNATURA AÑADIDA: " + asignatura.getAsignatura());
            return new ResponseEntity<>(asignatura, HttpStatus.OK);
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
