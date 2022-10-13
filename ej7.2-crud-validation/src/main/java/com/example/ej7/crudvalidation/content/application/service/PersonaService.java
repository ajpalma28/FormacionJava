package com.example.ej7.crudvalidation.content.application.service;

import com.example.ej7.crudvalidation.content.domain.Persona;
import com.example.ej7.crudvalidation.content.infrastructure.controller.feign.ProfesorFeignServer;
import com.example.ej7.crudvalidation.content.infrastructure.rest.spring.dto.ProfesorOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    PersonaRepository repository;
    @Autowired
    ProfesorFeignServer profesorFeignClient;

    public PersonaService(){

    }

    public void addPersona(Persona p){
        repository.save(p);
    }

    public Persona editaPersona(Persona p){
        repository.save(p);
        return p;
    }

    public void borraPersona(Persona p){
        repository.delete(p);
    }

    public Persona findById(String id){
        List<Persona> lista = new ArrayList<>();
        repository.findAll().forEach(lista::add);
        Optional<Persona> p = lista.stream().filter(x->x.getId().equals(id)).findFirst();
        return p.orElse(null);
    }

    public List<Persona> devuelveTodo(){
        List<Persona> lista = new ArrayList<>();
        repository.findAll().forEach(lista::add);
        return lista;
    }

    public Persona findByUsuario(String s){
        List<Persona> aux = new ArrayList<>();
        repository.findAll().forEach(aux::add);
        Optional<Persona> p = aux.stream().filter(x->x.getUsuario().equals(s)).findFirst();
        return p.orElse(null);
    }

    /*public ResponseEntity<ProfesorOutputDTO> getProfesor(String id){
        return profesorFeignClient.getProfesor(id);
    }*/


}
