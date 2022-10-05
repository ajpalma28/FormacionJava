package com.example.ej7.crudvalidation.content.infrastructure.controller.student;

import com.example.ej7.crudvalidation.content.application.service.PersonaService;
import com.example.ej7.crudvalidation.content.application.service.StudentService;
import com.example.ej7.crudvalidation.content.application.service.StudentServiceImpl;
import com.example.ej7.crudvalidation.content.domain.CustomError;
import com.example.ej7.crudvalidation.content.domain.Persona;
import com.example.ej7.crudvalidation.content.domain.Student;
import com.example.ej7.crudvalidation.content.domain.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.content.domain.exceptions.UnprocessableEntityException;
import com.example.ej7.crudvalidation.content.infrastructure.controller.ControllerAux;
import com.example.ej7.crudvalidation.content.infrastructure.controller.persona.PersonaControllerEdit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentControllerUpdate {

    @Autowired
    StudentService service;

    // Cargamos el logger
    Logger logger = LoggerFactory.getLogger(PersonaControllerEdit.class);

    ControllerAux aux = new ControllerAux();

    // Método para actualizar una persona: UPDATE en CRUD
    @PutMapping("/estudiante/{id}")
    public ResponseEntity<Object> editaEstudiante(@PathVariable String id, @RequestBody Student student){
        try {
            logger.info("ACTUALIZACIÓN DE ESTUDIANTE POR ID = "+id);
            aux.compruebaId(service, id);
            Student s1 = service.findById(id);
            logger.info("USUARIO A ACTUALIZAR: "+s1.toString());
            if(student.getNum_hours_week()!=null){
                s1.setNum_hours_week(student.getNum_hours_week());
            }
            if(student.getBranch()!=null){
                s1.setBranch(student.getBranch());
            }
            if(student.getPersona()!=null){
                s1.setPersona(student.getPersona());
            }
            if(student.getComments()!=null){
                s1.setComments(student.getComments());
            }
            service.editaStudent(s1);
            logger.info("ESTUDIANTE ACTUALIZADO: "+s1.toString());
            return new ResponseEntity<>(s1, HttpStatus.OK);
        } catch (EntityNotFoundException e){
            String error2 = "ESTUDIANTE NO ENCONTRADO.";
            logger.error(error2);
            logger.error(e.getMessage());
            return new ResponseEntity<>(new CustomError(404, error2),HttpStatus.NOT_FOUND);
        }
    }

}
