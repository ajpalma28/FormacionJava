package com.example.ej6.personcontrollers;

import com.example.ej6.personcontrollers.objetos.Ciudad;
import com.example.ej6.personcontrollers.objetos.Persona;
import com.example.ej6.personcontrollers.objetos.PersonaImpl;
import org.springframework.beans.factory.annotation.Qualifier;
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
	public List<Persona> devuelvePersonas(){
		return new ArrayList<Persona>();
	}

	@Bean
	public List<Ciudad> creaListaCiudades(){
		return new ArrayList<Ciudad>();
	}

	@Bean
	@Qualifier("bean1")
	public Persona bean1(){
		return new PersonaImpl("Joaquin", "Cadiz",37);
	}

	@Bean
	@Qualifier("bean2")
	public Persona bean2(){
		return new PersonaImpl("Ana", "Salamanca", 19);
	}

	@Bean
	@Qualifier("bean3")
	public Persona bean3(){
		return new PersonaImpl("Ezio", "Florencia", 34);
	}

}
