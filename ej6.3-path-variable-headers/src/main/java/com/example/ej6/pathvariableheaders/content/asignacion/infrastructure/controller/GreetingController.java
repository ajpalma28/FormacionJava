package com.example.ej6.pathvariableheaders.content.asignacion.infrastructure.controller;

import com.example.ej6.pathvariableheaders.content.asignacion.domain.Greeting;
import com.example.ej6.pathvariableheaders.content.asignacion.domain.GreetingImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String plantilla = "Hello, %s!";
    private static AtomicLong counter = new AtomicLong();

    @Autowired
    List<Greeting> saludos;

    Logger logger = LoggerFactory.getLogger(GreetingController.class);

    @GetMapping("/greeting")
    public Greeting generaGreting(@RequestParam(value="name", defaultValue="World") String name){
        Greeting g = new GreetingImpl(counter.incrementAndGet(), String.format(plantilla, name));
        saludos.add(g);
        logger.info("NUEVO SALUDO: "+g.getContent());
        return g;
    }

    @PostMapping(value = "/addGreeting", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Greeting nuevoGreeting(@RequestBody GreetingImpl greeting){
        saludos.add(greeting);
        logger.info("NUEVO SALUDO: "+greeting.getContent());
        logger.info("MOSTRAMOS LOS SALUDOS");
        saludos.forEach(x->logger.info(x.getContent()));
        return greeting;
    }

    @GetMapping("/user/{id}")
    public Greeting obtenGreeting(@PathVariable int id){
        logger.info("CONSULTA: "+saludos.get(id-1));
        return saludos.get(id-1);
    }

    @PutMapping(value = "/post")
    public Greeting modificaGreeting(@RequestParam(value="var1") int id, @RequestParam(value="var2") String content){
        Greeting g = saludos.get(id-1);
        logger.info("PROCEDEMOS A MODIFICAR EL SALUDO: "+g.getContent());
        g.setContent(content);
        logger.info("RESULTADO: "+g.getContent());
        logger.info("MOSTRAMOS LOS SALUDOS");
        saludos.forEach(x->logger.info(x.getContent()));
        return g;
    }

}
