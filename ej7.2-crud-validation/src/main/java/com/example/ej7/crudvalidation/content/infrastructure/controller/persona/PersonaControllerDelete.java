package com.example.ej7.crudvalidation.content.infrastructure.controller.persona;

import com.example.ej7.crudvalidation.content.application.service.PersonaService;
import com.example.ej7.crudvalidation.content.application.service.ProfesorService;
import com.example.ej7.crudvalidation.content.application.service.StudentService;
import com.example.ej7.crudvalidation.content.domain.CustomError;
import com.example.ej7.crudvalidation.content.domain.Persona;
import com.example.ej7.crudvalidation.content.domain.Profesor;
import com.example.ej7.crudvalidation.content.domain.Student;
import com.example.ej7.crudvalidation.content.domain.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.content.infrastructure.controller.ControllerAux;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PersonaControllerDelete {

    @Autowired
    PersonaService service;
    @Autowired
    ProfesorService ps;
    @Autowired
    StudentService ss;

    // Cargamos el logger
    Logger logger = LoggerFactory.getLogger(PersonaControllerEdit.class);

    ControllerAux aux = new ControllerAux();

    // Método para eliminar persona por ID: DELETE en CRUD
    @DeleteMapping("/persona/{id}")
    public ResponseEntity<Object> borraPersona(@PathVariable String id){
        try{
            aux.compruebaId(service,id);
            Persona p1 = service.findById(id);
            String rol = aux.devuelveRol(ps,ss,id);
            logger.info("SE BORRA EL USUARIO: "+p1.toString());
            switch (rol) {
                case "Profesor" -> {
                    Optional<Profesor> p = ps.getProfesores().stream().filter(x->x.getPersona().getId().equals(id)).findFirst();
                    p.ifPresent(profesor -> ps.eliminaProfesor(profesor));
                }
                case "Estudiante" -> {
                    Optional<Student> e = ss.getTodos().stream().filter(x->x.getPersona().getId().equals(id)).findFirst();
                    e.ifPresent(student -> ss.borraStudent(student));
                }
            }
            service.borraPersona(p1);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            logger.error("Usuario con ID "+id+" no borrado. RAZÓN:");
            logger.error(e.getMessage());
            return new ResponseEntity<>(new CustomError(400, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

}
