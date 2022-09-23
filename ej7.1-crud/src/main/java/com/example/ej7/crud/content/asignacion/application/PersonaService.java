package com.example.ej7.crud.content.asignacion.application;

import com.example.ej7.crud.content.asignacion.domain.Persona;
import com.example.ej7.crud.content.asignacion.domain.PersonaImpl;
import com.example.ej7.crud.content.asignacion.infrastructure.controller.PersonaController1;
import com.example.ej7.crud.content.asignacion.infrastructure.controller.PersonaController2;
import com.example.ej7.crud.content.asignacion.infrastructure.controller.PersonaController3;
import com.example.ej7.crud.content.asignacion.infrastructure.controller.PersonaController4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {

    @Autowired
    PersonaController1 controller1;

    @Autowired
    PersonaController2 controller2;

    @Autowired
    PersonaController3 controller3;

    @Autowired
    PersonaController4 controller4;

    public Persona anyadePersona(PersonaImpl p){
        return controller1.addPersona(p);
    }

    public Persona editaPersona(int id, PersonaImpl p){
        return controller2.editaPersona(id,p);
    }

    public void eliminaPersona(int id){
        controller3.borraPersona(id);
    }

    public Persona buscaPersonaID(int id){
        return controller4.getPersona(id);
    }

    public List<Persona> buscaPersonasNombre(String n){
        return controller4.getPersonaNombre(n);
    }

}
