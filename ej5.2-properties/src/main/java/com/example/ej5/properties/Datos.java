package com.example.ej5.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Datos implements CommandLineRunner {

    @Value("${greeting}")
    private String greeting;

    public String getGreeting() {
        return greeting;
    }

    @Value("${my.number}")
    private Integer myNumber;

    //@Value("${new.property:new.property no tiene valor}")
    //private String newProperty;

    @Autowired
    Environment environment;

    @Override
    public void run(String... args) throws Exception {
        if(greeting!=null){
            System.out.println("El valor de greeting es: "+greeting);
        }
        if(myNumber!=null){
            System.out.println("El valor de my.number es: "+myNumber);
        }
        if(environment!=null){
            System.out.println("El valor de new.property es: "+environment.getProperty("new.property"));
        }

    }
}
