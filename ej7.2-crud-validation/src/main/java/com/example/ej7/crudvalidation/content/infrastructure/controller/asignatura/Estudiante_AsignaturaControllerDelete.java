package com.example.ej7.crudvalidation.content.infrastructure.controller.asignatura;

import com.example.ej7.crudvalidation.content.application.service.Estudiante_AsignaturaService;
import com.example.ej7.crudvalidation.content.domain.CustomError;
import com.example.ej7.crudvalidation.content.domain.Estudiante_Asignatura;
import com.example.ej7.crudvalidation.content.domain.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.content.infrastructure.controller.ControllerAux;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class Estudiante_AsignaturaControllerDelete {

    @Autowired
    Estudiante_AsignaturaService service;

    Logger logger = LoggerFactory.logger(Estudiante_AsignaturaControllerDelete.class);

    ControllerAux aux = new ControllerAux();

    @DeleteMapping("/asignatura/{id}")
    public ResponseEntity<Object> eliminaAsignatura(@PathVariable String id){
        try{
            aux.compruebaId(service, id);
            Optional<Estudiante_Asignatura> ea = service.devuelveTodas().stream().filter(x-> x.getId_asignatura().equals(id)).findFirst();
            if(ea.isPresent()){
                String nombre = ea.get().getAsignatura();
                service.eliminaPorId(id);
                logger.info("BORRADA LA ASIGNATURA "+nombre);
                return new ResponseEntity<>(null, HttpStatus.OK);
            }else{
                logger.error("Asignatura no encontrada, no se puede borrar");
                return new ResponseEntity<>(new CustomError(404,"Asignatura no encontrada, no se puede borrar"),HttpStatus.NOT_FOUND);
            }
        } catch (EntityNotFoundException e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(new CustomError(404,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

}
