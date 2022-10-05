package com.example.ej7.crudvalidation.content.infrastructure.controller.persona;

import com.example.ej7.crudvalidation.content.application.service.PersonaService;
import com.example.ej7.crudvalidation.content.domain.CustomError;
import com.example.ej7.crudvalidation.content.domain.Persona;
import com.example.ej7.crudvalidation.content.domain.exceptions.UnprocessableEntityException;
import com.example.ej7.crudvalidation.content.infrastructure.controller.ControllerAux;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonaControllerCreate {

    @Autowired
    PersonaService service;

    // Cargamos el logger
    Logger logger = LoggerFactory.getLogger(PersonaControllerEdit.class);

    ControllerAux aux = new ControllerAux();

    // Método para añadir nueva persona: CREATE en CRUD
    @PostMapping("/persona")
    public ResponseEntity<Object> addPersona(@RequestBody Persona persona){
        try {
            aux.compruebaUsuario(persona.getUsuario());
            aux.compruebaFecha(persona.getCreated_date());
            aux.compruebaPW(persona.getPassword());
            aux.compruebaNombre(persona.getName());
            aux.compruebaAct(persona.getActive());
            aux.compruebaCiudad(persona.getCity());
            aux.compruebaEmail1(persona.getCompany_email());
            aux.compruebaEmail2(persona.getPersonal_email());
            //listaPersonas.add(persona);
            service.addPersona(persona);
            logger.info("NUEVA PERSONA AÑADIDA: " + persona.toString());
            return new ResponseEntity<>(persona, HttpStatus.OK);
        } catch (UnprocessableEntityException e1) {
            String errorr = "FORMATO DE CAMPO INCORRECTO.";
            logger.error(errorr);
            logger.error(e1.getMessage());
            return new ResponseEntity<>(new CustomError(422,errorr),HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity<>(new CustomError(400, e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
