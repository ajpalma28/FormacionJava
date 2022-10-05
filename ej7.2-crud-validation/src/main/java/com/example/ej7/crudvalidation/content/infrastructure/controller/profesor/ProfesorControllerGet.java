package com.example.ej7.crudvalidation.content.infrastructure.controller.profesor;

import com.example.ej7.crudvalidation.content.application.service.PersonaService;
import com.example.ej7.crudvalidation.content.application.service.ProfesorService;
import com.example.ej7.crudvalidation.content.domain.CustomError;
import com.example.ej7.crudvalidation.content.domain.Persona;
import com.example.ej7.crudvalidation.content.domain.Profesor;
import com.example.ej7.crudvalidation.content.domain.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.content.infrastructure.controller.ControllerAux;
import com.example.ej7.crudvalidation.content.infrastructure.rest.spring.dto.ProfesorOutputDTO;
import com.example.ej7.crudvalidation.content.infrastructure.rest.spring.dto.ProfesorPersonOutputDTO;
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
public class ProfesorControllerGet {

    @Autowired
    ProfesorService profesorService;

    @Autowired
    PersonaService personaService;

    Logger logger = LoggerFactory.getLogger(ProfesorControllerGet.class);

    ControllerAux aux = new ControllerAux();

    // Método para buscar un profesor por ID: READ en CRUD
    @GetMapping("/profesor/{id}")
    public ResponseEntity<Object> getProfesorId(@PathVariable String id, @RequestParam(required = false) String outputType){
        try {
            logger.info("BÚSQUEDA DE PROFESOR POR ID = "+id);
            aux.compruebaId(profesorService, id);
            Profesor p1 = profesorService.getById(id);
            logger.info("Profesor encontrado: "+p1.getId_profesor());
            if(outputType==null){
                ProfesorOutputDTO profesorOutputDTO = new ProfesorOutputDTO(p1.getId_profesor(),p1.getPersona(), p1.getComments(), p1.getBranch());
                return new ResponseEntity<>(profesorOutputDTO, HttpStatus.OK);
            }else{
                if(outputType.equals("simple")){
                    ProfesorOutputDTO profesorOutputDTO = new ProfesorOutputDTO(p1.getId_profesor(),p1.getPersona(), p1.getComments(), p1.getBranch());
                    return new ResponseEntity<>(profesorOutputDTO, HttpStatus.OK);
                }else if(outputType.equals("full")){
                    Persona p = personaService.findById(p1.getPersona().getId());
                    if(p==null){
                        throw new EntityNotFoundException("NO HAY PERSONA CON DICHO ID");
                    }else{
                        ProfesorPersonOutputDTO sp = new ProfesorPersonOutputDTO(p1,p);
                        return new ResponseEntity<>(sp, HttpStatus.OK);
                    }
                }
            }
            logger.info("Persona encontrada: "+p1.toString());
            return new ResponseEntity<>(p1, HttpStatus.OK);
        } catch (EntityNotFoundException e){
            String error1 ="PROFESOR NO ENCONTRADO.";
            logger.error(error1);
            logger.error(e.getMessage());
            return new ResponseEntity<>(new CustomError(404,error1),HttpStatus.NOT_FOUND);
        }

    }

    // Método para buscar todos los profesores: READ en CRUD
    @GetMapping("/profesores/all")
    public List<Profesor> getProfesores(){
        List<Profesor> lista = profesorService.getProfesores();
        if(lista.isEmpty()){
            logger.error("NO hay profesores registrados en la base de datos");
        }else{
            logger.info("PROFESORES REGISTRADOS: ");
            lista.forEach(x->logger.info(x.toString()));
        }
        return lista;
    }

}
