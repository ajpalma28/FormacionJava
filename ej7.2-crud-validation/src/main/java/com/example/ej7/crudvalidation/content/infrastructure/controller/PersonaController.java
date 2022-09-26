package com.example.ej7.crudvalidation.content.infrastructure.controller;

import com.example.ej7.crudvalidation.content.domain.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonaController {

    // Cargamos lista de personas
    @Autowired
    List<Persona> listaPersonas;

    // Cargamos el logger
    Logger logger = LoggerFactory.getLogger(PersonaController.class);


    // Método para añadir nueva persona: CREATE en CRUD
    @PostMapping("/persona")
    public Persona addPersona(@RequestBody Persona persona){
        try {
            persona.compruebaUsuario(persona.getUsuario());
            persona.compruebaFecha(persona.getCreatedDate());
            persona.compruebaPW(persona.getPassword());
            persona.compruebaNombre(persona.getName());
            persona.compruebaAct(persona.getActive());
            persona.compruebaCiudad(persona.getCity());
            persona.compruebaEmail1(persona.getCompanyEmail());
            persona.compruebaEmail2(persona.getPersonalEmail());
            listaPersonas.add(persona);
            logger.info("NUEVA PERSONA AÑADIDA: "+persona.toString());
            return persona;
        } catch (Exception e) {
            logger.error(e.toString());
            return null;
        }
    }

    // Método para actualizar una persona: UPDATE en CRUD
    @PutMapping("/persona/{id}")
    public Persona editaPersona(@PathVariable int id, @RequestBody Persona persona){
        Optional<Persona> p = listaPersonas.stream().filter(x->x.getId()==id).findFirst();
        Persona p1 = null;
        if(p.isPresent()){
            p1 = p.get();
            logger.info("USUARIO A ACTUALIZAR: "+p1.toString());
            if(persona.getActive()!=null){
                p1.setActive(persona.getActive());
            }
            if(persona.getCity()!=null){
                p1.setCity(persona.getCity());
            }
            if(p1.getCompanyEmail()!=null){
                p1.setCompanyEmail(persona.getCompanyEmail());
            }
            if(p1.getName()!=null){
                p1.setName(persona.getName());
            }
            if(p1.getPassword()!=null){
                p1.setPassword(persona.getPassword());
            }
            if(p1.getCreatedDate()!=null){
                p1.setCreatedDate(persona.getCreatedDate());
            }
            if(p1.getImagenUrl()!=null){
                p1.setImagenUrl(persona.getImagenUrl());
            }
            if(p1.getPersonalEmail()!=null){
                p1.setPersonalEmail(persona.getPersonalEmail());
            }
            if(p1.getSurname()!=null){
                p1.setSurname(persona.getSurname());
            }
            if(p1.getUsuario()!=null){
                p1.setUsuario(persona.getUsuario());
            }
            logger.info("USUARIO ACTUALIZADO: "+p1.toString());
        }else{
            logger.error("No se puede actualizar el usuario a los valores de "+persona.toString());
        }
        return p1;
    }

    // Método para buscar una persona por ID: READ en CRUD
    @GetMapping("/persona/id/{id}")
    public Persona getPersonaId(@PathVariable int id){
        logger.info("BÚSQUEDA DE PERSONA POR ID = "+id);
        Optional<Persona> p = listaPersonas.stream().filter(x->x.getId()==id).findFirst();
        Persona p1 = null;
        if(p.isPresent()){
            p1 = p.get();
            logger.info("Persona encontrada: "+p1.toString());
        }else{
            logger.error("PERSONA NO ENCONTRADA.");
        }
        return p1;
    }

    // Método para buscar una persona por nombre: READ en CRUD
    @GetMapping("/persona/usuario/{usuario}")
    public Persona getPersonaUsuario(@PathVariable String usuario){
        logger.info("BÚSQUEDA DE PERSONA POR USUARIO = "+usuario);
        Optional<Persona> p = listaPersonas.stream().filter(x->x.getUsuario().equals(usuario)).findFirst();
        Persona p1 = null;
        if(p.isPresent()){
            p1 = p.get();
            logger.info("Persona encontrada: "+p1.toString());
        }else{
            logger.error("PERSONA NO ENCONTRADA.");
        }
        return p1;
    }

    // Método para buscar todas las personas: READ en CRUD
    @GetMapping("/persona/all")
    public List<Persona> getListaPersonas(){
        return listaPersonas;
    }

    // Método para eliminar persona por ID: DELETE en CRUD
    @DeleteMapping("/persona/{id}")
    public void borraPersona(@PathVariable int id){
        Optional<Persona> p = listaPersonas.stream().filter(x->x.getId()==id).findFirst();
        if(p.isEmpty()){
            logger.error("ERROR: No existe usuario con ese ID. Por tanto, no se bora nada.");
        }else{
            logger.info("SE BORRA EL USUARIO: "+p.get().toString());
            listaPersonas.remove(p.get());
        }
    }

}
