package com.example.ej6.personcontrollers;

import com.example.ej6.personcontrollers.objetos.Ciudad;
import com.example.ej6.personcontrollers.objetos.CiudadImpl;
import com.example.ej6.personcontrollers.objetos.Persona;
import com.example.ej6.personcontrollers.objetos.PersonaImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/controlador1")
public class Controlador1 {

    @Autowired
    List<Persona> personas;

    @Autowired
    List<Ciudad> ciudades;

    // TODO: /controlador1/addPersona -> tipo GET, headers: nombre, población, edad
    Logger logger = LoggerFactory.getLogger(Controlador1.class);

    @GetMapping("/addPersona")
    public Persona controlaPersona(@RequestHeader Map<String, String> cabecera){
        String nombre = cabecera.get("name");
        String edad = cabecera.get("edad");
        String ciudad = cabecera.get("ciudad");
        Ciudad c = new CiudadImpl(ciudad);
        if(ciudades.contains(c)){
            int pos = ciudades.indexOf(c);
            ciudades.get(pos).setNumeroHabitantes();
        }
        Persona p = new PersonaImpl(nombre, ciudad, Integer.parseInt(edad));
        logger.info("NUEVA PERSONA: "+p.toString());
        personas.add(p);
        return p;
    }

    @PostMapping(value = "/addCiudad")
    @ResponseStatus(HttpStatus.OK)
    public Ciudad creaCiudad(@RequestHeader Map<String, String> c){
        String c1 = c.get("nombre");
        Ciudad ciudad = new CiudadImpl(c1);
        ciudades.add(ciudad);
        logger.info("NUEVA CIUDAD -> "+ciudad.toString());
        return ciudad;
    }

    @GetMapping("/getCiudad")
    public List<Ciudad> getCiudades(){
        logger.info("OBTENEMOS LISTA DE CIUDADES");
        ciudades.stream().forEach(x->logger.info(x.toString()));
        return ciudades;
    }

}
