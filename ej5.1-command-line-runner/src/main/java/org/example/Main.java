package org.example;

//import org.sprintframework.data.repository;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    @Bean
    CommandLineRunner ejecutame(){
        return p -> {
            System.out.println("Linea a ejecutar cuando arranque");
        }
    }
}