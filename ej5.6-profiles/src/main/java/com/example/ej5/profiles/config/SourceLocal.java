package com.example.ej5.profiles.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource("classpath:application-local.properties")
@Profile("local")
@Component
public class SourceLocal {

    @Value("${environment}")
    private String env;

    @Value("${bd.url}")
    private String url;

    public void run(){
        System.out.println("PERFIL LOCAL ACTIVADO");
    }
}
