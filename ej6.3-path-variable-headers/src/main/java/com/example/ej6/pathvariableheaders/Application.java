package com.example.ej6.pathvariableheaders;

import com.example.ej6.pathvariableheaders.content.asignacion.domain.Greeting;
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
	public List<Greeting> cargaSaludos(){
		return new ArrayList<Greeting>();
	}

}
