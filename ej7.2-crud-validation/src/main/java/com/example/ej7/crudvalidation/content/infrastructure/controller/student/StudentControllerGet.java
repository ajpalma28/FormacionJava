package com.example.ej7.crudvalidation.content.infrastructure.controller.student;

import com.example.ej7.crudvalidation.content.application.service.PersonaService;
import com.example.ej7.crudvalidation.content.application.service.StudentService;
import com.example.ej7.crudvalidation.content.domain.CustomError;
import com.example.ej7.crudvalidation.content.domain.Persona;
import com.example.ej7.crudvalidation.content.domain.Student;
import com.example.ej7.crudvalidation.content.domain.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.content.infrastructure.controller.ControllerAux;
import com.example.ej7.crudvalidation.content.infrastructure.controller.persona.PersonaControllerEdit;
import com.example.ej7.crudvalidation.content.infrastructure.rest.spring.dto.StudentOutputDTO;
import com.example.ej7.crudvalidation.content.infrastructure.rest.spring.dto.StudentPersonOutputDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentControllerGet {

    @Autowired
    StudentService service;

    @Autowired
    PersonaService personaService;

    // Cargamos el logger
    Logger logger = LoggerFactory.getLogger(PersonaControllerEdit.class);

    ControllerAux aux = new ControllerAux();

    // Método para buscar una persona por ID: READ en CRUD
    /*@GetMapping("/estudiante/{id}")
    public ResponseEntity<Object> getStudentId(@PathVariable int id, @RequestParam(required = false) String outputType){
        try {
            logger.info("BÚSQUEDA DE ESTUDIANTE POR ID = "+id);
            aux.compruebaId(service, id);
            Student p1 = service.findById(id);
            logger.info("Estudiante encontrado: "+p1.getId_student());
            if(outputType==null){
                StudentOutputDTO studentOutputDTO = new StudentOutputDTO(p1.getId_student(),p1.getPersona(), p1.getNum_hours_week(),
                        p1.getComments(), p1.getBranch());
                return new ResponseEntity<>(studentOutputDTO, HttpStatus.OK);
            }else{
                if(outputType.equals("simple")){
                    StudentOutputDTO studentOutputDTO = new StudentOutputDTO(p1.getId_student(),p1.getPersona(), p1.getNum_hours_week(),
                            p1.getComments(), p1.getBranch());
                    return new ResponseEntity<>(studentOutputDTO, HttpStatus.OK);
                }else if(outputType.equals("full")){
                    Persona p = personaService.findById(p1.getPersona().getId());
                    if(p==null){
                        throw new EntityNotFoundException("NO HAY PERSONA CON DICHO ID");
                    }else{
                        StudentPersonOutputDTO sp = new StudentPersonOutputDTO(p1,p);
                        return new ResponseEntity<>(sp, HttpStatus.OK);
                    }
                }
            }
            logger.info("Persona encontrada: "+p1.toString());
            return new ResponseEntity<>(p1, HttpStatus.OK);
        } catch (EntityNotFoundException e){
            String error1 ="ESTUDIANTE NO ENCONTRADO.";
            logger.error(error1);
            logger.error(e.getMessage());
            return new ResponseEntity<>(new CustomError(404,error1),HttpStatus.NOT_FOUND);
        }

    }*/

    // Método para buscar todas las personas: READ en CRUD
    @GetMapping("/estudiantes/all")
    public List<Student> getStudents(){
        List<Student> lista = service.getTodos();
        if(lista.isEmpty()){
            logger.error("NO hay estudiantes registrados en la base de datos");
        }else{
            logger.info("ESTUDIANTES REGISTRADOS: ");
            lista.forEach(x->logger.info(x.toString()));
        }
        return lista;
    }

}
