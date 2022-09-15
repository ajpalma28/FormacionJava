package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		primera();
	}


	@PostConstruct
	public static void primera(){
		String x = "MANQUE";
		String y = "PIERDA";
		System.out.println("Hola desde clase inicial");
	}

	@Bean
	CommandLineRunner tercero(){
		return p -> {
			System.out.println("Soy la tercera clase ");
		};
	}
	@Bean
	CommandLineRunner ejecutame(){
		return p -> {
			System.out.println("Hola desde clase secundaria");
		};
	}




}
