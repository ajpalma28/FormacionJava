package com.example.ej7.crudvalidation.content.infrastructure.controller.persona;

import com.example.ej7.crudvalidation.content.application.service.PersonaService;
import com.example.ej7.crudvalidation.content.domain.CustomError;
import com.example.ej7.crudvalidation.content.domain.Persona;
import com.example.ej7.crudvalidation.content.domain.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.content.domain.exceptions.UnprocessableEntityException;
import com.example.ej7.crudvalidation.content.infrastructure.controller.ControllerAux;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonaControllerEdit {

    @Autowired
    PersonaService service = new PersonaService();

    // Cargamos el logger
    Logger logger = LoggerFactory.getLogger(PersonaControllerEdit.class);

    ControllerAux aux = new ControllerAux();

    // Método para actualizar una persona: UPDATE en CRUD
    @PutMapping("/persona/{id}")
    public ResponseEntity<Object> editaPersona(@PathVariable String id, @RequestBody Persona persona){
        try {
            logger.info("ACTUALIZACIÓN DE PERSONA POR ID = "+id);
            aux.compruebaId(service, id);
            Persona p1 = service.findById(id);
            logger.info("USUARIO A ACTUALIZAR: "+p1.toString());
            if(persona.getActive()!=null){
                aux.compruebaAct(persona.getActive());
                p1.setActive(persona.getActive());
            }
            if(persona.getCity()!=null){
                aux.compruebaCiudad(persona.getCity());
                p1.setCity(persona.getCity());
            }
            if(persona.getCompany_email()!=null){
                aux.compruebaEmail1(persona.getCompany_email());
                p1.setCompany_email(persona.getCompany_email());
            }
            if(persona.getName()!=null){
                aux.compruebaNombre(persona.getName());
                p1.setName(persona.getName());
            }
            if(persona.getPassword()!=null){
                aux.compruebaPW(persona.getPassword());
                p1.setPassword(persona.getPassword());
            }
            if(persona.getCreated_date()!=null){
                aux.compruebaFecha(persona.getCreated_date());
                p1.setCreated_date(persona.getCreated_date());
            }
            if(persona.getImagen_url()!=null){
                p1.setImagen_url(persona.getImagen_url());
            }
            if(persona.getPersonal_email()!=null){
                aux.compruebaEmail2(persona.getPersonal_email());
                p1.setPersonal_email(persona.getPersonal_email());
            }
            if(persona.getSurname()!=null){
                p1.setSurname(persona.getSurname());
            }
            if(persona.getUsuario()!=null){
                aux.compruebaUsuario(persona.getUsuario());
                p1.setUsuario(persona.getUsuario());
            }
            if(persona.getTermination_date()!=null){
                p1.setTermination_date(persona.getTermination_date());
            }
            service.editaPersona(p1);
            logger.info("USUARIO ACTUALIZADO: "+p1.toString());
            return new ResponseEntity<>(p1,HttpStatus.OK);
        } catch (EntityNotFoundException e){
            String error2 = "PERSONA NO ENCONTRADA.";
            logger.error(error2);
            logger.error(e.getMessage());
            return new ResponseEntity<>(new CustomError(404, error2),HttpStatus.NOT_FOUND);
        } catch (UnprocessableEntityException e1) {
            String error1 = "FORMATO DE CAMPO INCORRECTO.";
            logger.error(error1);
            logger.error(e1.getMessage());
            return new ResponseEntity<>(new CustomError(422, error1),HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
