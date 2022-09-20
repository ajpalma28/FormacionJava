package com.example.ej6.simplecontrollers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class User {

    Logger logger = LoggerFactory.getLogger(User.class);

    @GetMapping(value = "/{name}")
    public String leeNombre(@PathVariable String name){
        logger.info("Enviamos saludo a "+name);
        return "Hola "+name;
    }


}
