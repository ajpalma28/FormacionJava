package com.example.ej7.crud;

import com.example.ej7.crud.content.asignacion.domain.Persona;
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
	public List<Persona> cargaLista(){
		return new ArrayList<Persona>();
	}

}
