package com.example.ej5.profiles.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;

@Configuration
@PropertySource("application-int.properties")
@Profile("INT")
@Service
@ActiveProfiles("INT")
public class SourceInt {

    @Value("${environment}")
    private String env;

    @Value("${bd.url}")
    private String url;

    public void run(){
        System.out.println("PERFIL INT ACTIVADO "+url);
    }
}