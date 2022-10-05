package com.example.ej7.crudvalidation.content.infrastructure.controller.student;

import com.example.ej7.crudvalidation.content.application.service.StudentService;
import com.example.ej7.crudvalidation.content.domain.CustomError;
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
public class StudentControllerDelete {

    @Autowired
    StudentService service;

    // Cargamos el logger
    Logger logger = LoggerFactory.getLogger(StudentControllerDelete.class);

    ControllerAux aux = new ControllerAux();

    // Método para eliminar estudiante por ID: DELETE en CRUD
    @DeleteMapping("/estudiante/{id}")
    public ResponseEntity<Object> borraEstudiante(@PathVariable String id){
        try{
            aux.compruebaId(service,id);
            Student p1 = service.findById(id);
            logger.info("SE BORRA EL ESTUDIANTE: "+p1.toString());
            service.borraStudent(p1);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            logger.error("Estudiante con ID "+id+" no borrado. RAZÓN:");
            logger.error(e.getMessage());
            return new ResponseEntity<>(new CustomError(400, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

}
