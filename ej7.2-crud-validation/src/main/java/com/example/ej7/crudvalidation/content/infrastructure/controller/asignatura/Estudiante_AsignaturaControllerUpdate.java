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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class Estudiante_AsignaturaControllerUpdate {

    @Autowired
    Estudiante_AsignaturaService service;

    ControllerAux aux = new ControllerAux();

    Logger logger = LoggerFactory.logger(Estudiante_AsignaturaControllerUpdate.class);

    @PutMapping("/asignatura/{id}")
    public ResponseEntity<Object> actualizaAsignatura(@PathVariable String id, @RequestBody Estudiante_Asignatura asignatura){
        try{
            aux.compruebaId(service,id);
            Optional<Estudiante_Asignatura> ea = service.devuelveTodas().stream().filter(x-> x.getId_asignatura().equals(id)).findFirst();
            if(ea.isPresent()){
                if(asignatura.getAsignatura()!=null){
                    ea.get().setAsignatura(asignatura.getAsignatura());
                }
                if(asignatura.getComments()!=null){
                    ea.get().setComments(asignatura.getComments());
                }
                if(asignatura.getInitial_date()!=null){
                    ea.get().setInitial_date(asignatura.getInitial_date());
                }
                if(asignatura.getFinish_date()!=null){
                    ea.get().setFinish_date(asignatura.getFinish_date());
                }
                logger.info("ASIGNATURA ACTUALIZADA: "+ea.get().getAsignatura());
                service.modificaAsignatura(ea.get());
                return new ResponseEntity<>(ea.get(),HttpStatus.OK);
            }else{
                logger.error("No se ha encontrado la asignatura");
                return new ResponseEntity<>(new CustomError(404,"No se ha encontrado la asignatura"), HttpStatus.NOT_FOUND);
            }
        } catch (EntityNotFoundException e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(new CustomError(404,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

}
