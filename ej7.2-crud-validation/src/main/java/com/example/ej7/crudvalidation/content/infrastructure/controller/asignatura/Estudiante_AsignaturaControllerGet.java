package com.example.ej7.crudvalidation.content.infrastructure.controller.asignatura;

import com.example.ej7.crudvalidation.content.application.service.Estudiante_AsignaturaService;
import com.example.ej7.crudvalidation.content.domain.CustomError;
import com.example.ej7.crudvalidation.content.domain.Estudiante_Asignatura;
import com.example.ej7.crudvalidation.content.domain.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.content.infrastructure.controller.ControllerAux;
import com.example.ej7.crudvalidation.content.infrastructure.rest.spring.dto.Estudiante_AsignaturaOutputDTO;
import com.example.ej7.crudvalidation.content.infrastructure.rest.spring.dto.StudentAsignOutputDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Estudiante_AsignaturaControllerGet {

    @Autowired
    Estudiante_AsignaturaService service;

    // Cargamos el logger
    Logger logger = LoggerFactory.getLogger(Estudiante_AsignaturaControllerGet.class);

    ControllerAux aux = new ControllerAux();

    @GetMapping("/asignatura/{id}")
    public ResponseEntity<Object> getAsignatura(@PathVariable String id) {
        try {
            Estudiante_Asignatura ea = service.findById(id);
            aux.compruebaId(service, id);
            if (ea != null) {
                logger.info("Asignatura encontrada: " + ea.getAsignatura());
                return new ResponseEntity<>(new Estudiante_AsignaturaOutputDTO(ea), HttpStatus.OK);
            } else {
                String s = "No se ha encontrado la asignatura";
                logger.error(s);
                return new ResponseEntity<>(new CustomError(404, s), HttpStatus.NOT_FOUND);
            }
        } catch (EntityNotFoundException e) {
            String error1 = "ASIGNATURA NO ENCONTRADA.";
            logger.error(error1);
            logger.error(e.getMessage());
            return new ResponseEntity<>(new CustomError(404, error1), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/asignatura/nombre/{s}")
    public ResponseEntity<Object> getAsignaturaNombre(@PathVariable String s) {
        Estudiante_Asignatura ea = service.devuelvePorNombre(s);
        if (ea != null) {
            logger.info("Asignatura encontrada: " + ea.getAsignatura());
            return new ResponseEntity<>(ea, HttpStatus.OK);
        } else {
            String s1 = "No se ha encontrado la asignatura";
            logger.error(s1);
            return new ResponseEntity<>(new CustomError(404, s1), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/asignatura/alumno/{id}")
    public ResponseEntity<Object> getAsignaturasPorAlumno(@PathVariable String id){
        logger.info("Buscamos por id de alumno: "+id);
        List<Estudiante_Asignatura> list = service.buscaPorIdEstudiante(id);
        List<Estudiante_AsignaturaOutputDTO> lista2 = new ArrayList<>();
        logger.info("ASIGNATURAS DEL ALUMNO "+id);
        list.forEach(x -> {
            logger.info(x.toString());
            lista2.add(new Estudiante_AsignaturaOutputDTO(x));
        });
        return new ResponseEntity<>(lista2, HttpStatus.OK);
    }

    @GetMapping("/asignaturas")
    public ResponseEntity<Object> getAsignaturasTODAS(){
        logger.info("Buscamos todas las asignaturas");
        List<Estudiante_Asignatura> list = service.devuelveTodas();
        List<Estudiante_AsignaturaOutputDTO> lista2 = new ArrayList<>();
        list.forEach(x-> {
            logger.info(x.toString());
            lista2.add(new Estudiante_AsignaturaOutputDTO(x));
        });
        return new ResponseEntity<>(lista2, HttpStatus.OK);
    }

}
