package com.example.ej7.crudvalidation.content.infrastructure.controller.student;

import com.example.ej7.crudvalidation.content.application.service.PersonaService;
import com.example.ej7.crudvalidation.content.application.service.ProfesorService;
import com.example.ej7.crudvalidation.content.application.service.StudentService;
import com.example.ej7.crudvalidation.content.domain.CustomError;
import com.example.ej7.crudvalidation.content.domain.Student;
import com.example.ej7.crudvalidation.content.domain.exceptions.RolPersonException;
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
public class StudentControllerCreate {

    @Autowired
    StudentService studentService;
    @Autowired
    ProfesorService profesorService;
    @Autowired
    PersonaService personaService;

    // Cargamos el logger
    Logger logger = LoggerFactory.getLogger(PersonaControllerEdit.class);

    ControllerAux aux = new ControllerAux();

    // Método para añadir nueva persona: CREATE en CRUD
    @PostMapping("/estudiante")
    public ResponseEntity<Object> addEstudiante(@RequestBody Student student){
        try {
            aux.compruebaNumHours(student.getNum_hours_week());
            aux.compruebaBranch(student.getBranch());
            aux.compruebaRolCorrecto(profesorService,student.getPersona().getId());
            student.setPersona(personaService.findById(student.getPersona().getId()));
            studentService.addStudent(student);
            logger.info("NUEVO ESTUDIANTE AÑADIDO: " + student.toString());
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (UnprocessableEntityException e1) {
            String errorr = "FORMATO DE CAMPO INCORRECTO.";
            logger.error(errorr);
            logger.error(e1.getMessage());
            return new ResponseEntity<>(new CustomError(422,errorr),HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (RolPersonException rpe) {
            logger.error(rpe.getMessage());
            return new ResponseEntity<>(new CustomError(409, rpe.getMessage()),HttpStatus.CONFLICT);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity<>(new CustomError(400, e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
