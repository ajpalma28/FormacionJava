package com.example.ej7.crudvalidation;

import com.example.ej7.crudvalidation.content.domain.Persona;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public List<Persona> cargaPersonas(){
		return new ArrayList<Persona>();
	}

}
