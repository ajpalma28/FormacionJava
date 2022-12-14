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
import com.example.ej7.crudvalidation.content.infrastructure.controller.feign.ProfesorFeignServer;
import com.example.ej7.crudvalidation.content.infrastructure.rest.spring.dto.ProfesorOutputDTO;
import com.example.ej7.crudvalidation.content.infrastructure.rest.spring.dto.ProfesorPersonOutputDTO;
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
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonaControllerGet {

    @Autowired
    PersonaService service;
    @Autowired
    ProfesorService ps;
    @Autowired
    StudentService ss;
    @Autowired
    ProfesorFeignServer profesorFeignClient;

    // Cargamos el logger
    Logger logger = LoggerFactory.getLogger(PersonaControllerEdit.class);

    ControllerAux aux = new ControllerAux();

    // M??todo para buscar una persona por ID: READ en CRUD
    @GetMapping("/persona/id/{id}")
    public ResponseEntity<Object> getPersonaId(@PathVariable String id, @RequestParam(required = false) String outputType) {
        try {
            logger.info("B??SQUEDA DE PERSONA POR ID = " + id);
            aux.compruebaId(service, id);
            Persona p1 = service.findById(id);
            String rol = aux.devuelveRol(ps, ss, id);
            if (outputType != null) {
                if (outputType.equals("simple")) {
                    return new ResponseEntity<>(p1, HttpStatus.OK);
                } else if (outputType.equals("full")) {
                    switch (rol) {
                        case "Profesor":
                            Optional<Profesor> p = ps.getProfesores().stream().filter(x -> x.getPersona().getId().equals(id)).findFirst();
                            if (p.isPresent()) {
                                ProfesorPersonOutputDTO profesorPersonOutputDTO = new ProfesorPersonOutputDTO(p.get(), p1);
                                return new ResponseEntity<>(profesorPersonOutputDTO, HttpStatus.OK);
                            }
                        case "Estudiante":
                            Optional<Student> s = ss.getTodos().stream().filter(x -> x.getPersona().getId().equals(id)).findFirst();
                            if (s.isPresent()) {
                                StudentPersonOutputDTO studentPersonOutputDTO = new StudentPersonOutputDTO(s.get(), p1);
                                return new ResponseEntity<>(studentPersonOutputDTO, HttpStatus.OK);
                            }
                    }
                }
            }
            logger.info("Persona encontrada: " + p1.toString());
            return new ResponseEntity<>(p1, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            String error1 = "PERSONA NO ENCONTRADA.";
            logger.error(error1);
            logger.error(e.getMessage());
            return new ResponseEntity<>(new CustomError(404, error1), HttpStatus.NOT_FOUND);
        }

    }

    // M??todo para buscar una persona por nombre: READ en CRUD
    @GetMapping("/persona/usuario/{usuario}")
    public ResponseEntity<Object> getPersonaUsuario(@PathVariable String usuario, @RequestParam(required = false) String outputType) {
        logger.info("B??SQUEDA DE PERSONA POR USUARIO = " + usuario);
        Persona p1 = service.findByUsuario(usuario);
        if (p1 == null) {
            logger.error("PERSONA NO ENCONTRADA.");
            return null;
        } else {
            logger.info("Persona encontrada: " + p1.toString());
            String rol = aux.devuelveRol(ps, ss, p1.getId());
            if (outputType != null) {
                if (outputType.equals("simple")) {
                    return new ResponseEntity<>(p1, HttpStatus.OK);
                } else if (outputType.equals("full")) {
                    switch (rol) {
                        case "Profesor":
                            Optional<Profesor> p = ps.getProfesores().stream().filter(x -> x.getPersona().getId().equals(p1.getId())).findFirst();
                            if (p.isPresent()) {
                                ProfesorPersonOutputDTO profesorPersonOutputDTO = new ProfesorPersonOutputDTO(p.get(), p1);
                                return new ResponseEntity<>(profesorPersonOutputDTO, HttpStatus.OK);
                            }
                        case "Estudiante":
                            Optional<Student> s = ss.getTodos().stream().filter(x -> x.getPersona().getId().equals(p1.getId())).findFirst();
                            if (s.isPresent()) {
                                StudentPersonOutputDTO studentPersonOutputDTO = new StudentPersonOutputDTO(s.get(), p1);
                                return new ResponseEntity<>(studentPersonOutputDTO, HttpStatus.OK);
                            }
                    }
                }
            }

        }
        return new ResponseEntity<>(p1,HttpStatus.OK);
    }

    // M??todo para buscar todas las personas: READ en CRUD
    @GetMapping("/persona/all")
    public List<Object> getListaPersonas(@RequestParam(required = false) String outputType) {
        List<Persona> lista = service.devuelveTodo();
        List<Object> res = new ArrayList<>();
        if (lista.isEmpty()) {
            logger.error("NO hay personas registradas en la base de datos");
        } else {
            if(outputType!=null && outputType.equals("full")){
               lista.forEach(x->{
                   String rol = aux.devuelveRol(ps, ss, x.getId());
                   List<Student> list1 = ss.getTodos();
                   List<Profesor> list2 = ps.getProfesores();
                   switch (rol) {
                       case "Profesor" -> {
                           Optional<Profesor> prof = list2.stream().filter(x2 -> x2.getPersona().getId().equals(x.getId())).findFirst();
                           prof.ifPresent(profesor -> res.add(new ProfesorPersonOutputDTO(profesor, x)));
                       }
                       case "Estudiante" -> {
                           Optional<Student> e = list1.stream().filter(x1 -> x1.getPersona().getId().equals(x.getId())).findFirst();
                           e.ifPresent(student -> res.add(new StudentPersonOutputDTO(student, x)));
                       }
                       default -> res.add(x);
                   }

               });
               logger.info("PERSONAS REGISTRADAS: ");
               res.forEach(x3->{
                   logger.info(x3.toString());
               });
            }else{
                logger.info("PERSONAS REGISTRADAS: ");
                lista.forEach(x -> {
                    logger.info(x.toString());
                    res.add(x);
                });
            }
        }
        return res;
    }

    @GetMapping("/persona/profesor/{id}")
    public ResponseEntity<Object> getProfesorId(@PathVariable String id){
        //ResponseEntity<ProfesorOutputDTO> rs = new RestTemplate().getForEntity("http://localhost:8081/persona/profesor/"+id, ProfesorOutputDTO.class);
        ResponseEntity<Object> rs = profesorFeignClient.getProfesorId(id);
        /*ProfesorOutputDTO prof = new ProfesorOutputDTO(service.getProfesor(id).getId_profesor(), service.getProfesor(id).getPersona(),
                service.getProfesor(id).getComments(), service.getProfesor(id).getBranch());
        return ResponseEntity.ok(prof);*/
        return ResponseEntity.ok(rs.getBody());
    }

}
