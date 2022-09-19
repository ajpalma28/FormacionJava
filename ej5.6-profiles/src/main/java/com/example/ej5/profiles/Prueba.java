package com.example.ej5.profiles;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;
import java.util.Scanner;

@SpringBootApplication
public class Prueba implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Prueba.class, args);
        System.out.println("Application running in the dev mode !!");
    }

    @Override
    public void run(String... args) throws Exception {
        InputStream stream = System.in;
        Scanner scanner = new Scanner(stream);
        System.out.println("Introduzca el modo de funcionamiento (local/INT/PRO): ");
        String entrada = scanner.next();
        switch(entrada){
            case "local":
                // TODO
                System.out.println("RECIBIDO LOCAL");
                break;
            case "INT":
                // TODO
                System.out.println("RECIBIDO INT");
                break;
            case "PRO":
                // TODO
                System.out.println("RECIBIDO PRO");
                break;
            default:
                System.out.println("Comando no reconocido.");
                break;
        }
    }
}