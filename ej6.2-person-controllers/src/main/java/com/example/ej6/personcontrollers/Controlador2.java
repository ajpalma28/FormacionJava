package com.example.ej6.personcontrollers;

import com.example.ej6.personcontrollers.objetos.Persona;
import com.example.ej6.personcontrollers.objetos.PersonaImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/controlador2")
public class Controlador2 {

    // TODO: /controlador2/getPersona -> devolver Persona recibido en Controlador1 multiplicando la edad por 2

    @Autowired
    List<Persona> personas;

    Logger logger = LoggerFactory.getLogger(Controlador2.class);

    @GetMapping("/getPersona")
    public Persona obtenPersona(@RequestHeader Map<String, String> cabecera){
        String nombre = cabecera.get("nombre");
        String ciudad = cabecera.get("ciudad");
        Persona p = new PersonaImpl(nombre,ciudad,0);
        logger.info("RECIBE PERSONA -> "+p.toString());
        Persona p1 = new PersonaImpl("","",0);
        Optional<Persona> p3 = personas.stream().filter(x->x.equals(p)).findFirst();
        if(p3.isPresent()){
            p1.setNombre(p3.get().getNombre());
            p1.setPoblacion(p3.get().getPoblacion());
            p1.setEdad(p3.get().getEdad()*2);
            logger.info("Persona encontrada: "+p3.get().toString());
        }
        if(p1.getPoblacion().equals("")){
            logger.error("Persona no encontrada");
        }else{
            logger.info("Devuelve: "+p1.toString());
        }
        return p1;
    }

}
