package com.example.ej7.crudvalidation.content.infrastructure.controller.student;

import com.example.ej7.crudvalidation.content.application.service.StudentService;
import com.example.ej7.crudvalidation.content.domain.CustomError;
import com.example.ej7.crudvalidation.content.domain.Estudiante_Asignatura;
import com.example.ej7.crudvalidation.content.domain.Student;
import com.example.ej7.crudvalidation.content.domain.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.content.infrastructure.controller.ControllerAux;
import com.example.ej7.crudvalidation.content.infrastructure.controller.persona.PersonaControllerEdit;
import com.example.ej7.crudvalidation.content.infrastructure.rest.spring.dto.StudentAsignOutputDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PutMapping("/estudiante/addAsignaturas/{id}")
    public ResponseEntity<Object> addAsignaturas(@PathVariable String id, @RequestBody List<Estudiante_Asignatura> lista){
        try {
            logger.info("Añadimos NUEVAS asignaturas al estudiante");
            lista.forEach(x->logger.info(x.toString()));
            aux.compruebaId(service, id);
            Student s1 = service.findById(id);
            if(s1!=null){
                service.addAsignaturas(id, lista);
            }
            return ResponseEntity.ok().body(new StudentAsignOutputDTO(s1));
        } catch (EntityNotFoundException e1) {
            String error2 = "ESTUDIANTE NO ENCONTRADO.";
            logger.error(error2);
            logger.error(e1.getMessage());
            return new ResponseEntity<>(new CustomError(404, error2),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/estudiante/removeAsignaturas/{id}")
    public ResponseEntity<Object> removeAsignaturas(@PathVariable String id, @RequestBody List<String> lista){
        try {
            logger.info("Borramos las asignaturas al estudiante");
            lista.forEach(x->logger.info(x.toString()));
            aux.compruebaId(service, id);
            Student s1 = service.findById(id);
            if(s1!=null){
                service.removeAsignaturas(id,lista);
            }
            return new ResponseEntity<>(new StudentAsignOutputDTO(s1),HttpStatus.OK);
        } catch (EntityNotFoundException e1) {
            String error2 = "ESTUDIANTE NO ENCONTRADO.";
            logger.error(error2);
            logger.error(e1.getMessage());
            return new ResponseEntity<>(new CustomError(404, error2),HttpStatus.NOT_FOUND);
        }
    }

}
