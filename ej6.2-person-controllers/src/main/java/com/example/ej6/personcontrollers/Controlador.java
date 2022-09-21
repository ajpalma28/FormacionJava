package com.example.ej6.personcontrollers;

import com.example.ej6.personcontrollers.objetos.Persona;
import com.example.ej6.personcontrollers.objetos.PersonaImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlador")
public class Controlador {

    @Autowired
    @Qualifier("bean1")
    Persona p1;

    @Autowired
    @Qualifier("bean2")
    Persona p2;

    @Autowired
    @Qualifier("bean3")
    Persona p3;

    Logger logger = LoggerFactory.getLogger(Controlador.class);

    @GetMapping("/bean/{bean}")
    public Persona devuelvePersona(@PathVariable String bean){
        Persona p = new PersonaImpl("","",0);
        switch (bean) {
            case "bean1" -> {
                p.setEdad(p1.getEdad());
                p.setPoblacion(p1.getPoblacion());
                p.setNombre(p1.getNombre());
            }
            case "bean2" -> {
                p.setEdad(p2.getEdad());
                p.setPoblacion(p2.getPoblacion());
                p.setNombre(p2.getNombre());
            }
            case "bean3" -> {
                p.setEdad(p3.getEdad());
                p.setPoblacion(p3.getPoblacion());
                p.setNombre(p3.getNombre());
            }
        }
        if(p.getNombre().equals("")){
            logger.error("PERSONA NO ENCONTRADA");
        }else{
            logger.info("Devolvemos -> "+p.toString());
        }
        return p;
    }

}
