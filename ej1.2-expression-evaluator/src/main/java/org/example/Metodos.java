package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Metodos {

    public static void lecturaFichero1(String ubicacion) {
        try {
            Scanner scanner = new Scanner(new File(ubicacion));
            scanner.useDelimiter("\r\n");
            while (scanner.hasNext()) {
                String aux = scanner.next();
                if(aux.contains("“")){
                    System.out.println(aux+" es una cadena de texto");
                }else if(aux.contains(">")){
                    System.out.println(aux+" es una fecha");
                }else if(aux.contains("*") || aux.contains("+") || aux.contains("-") || aux.contains("/")){
                    System.out.println(aux+" es una operación");
                    if(aux.contains("*")){
                        String[] aux2 = aux.split("\\*");
                        System.out.println("Resultado: "+(Integer.parseInt(aux2[0].trim())*Integer.parseInt(aux2[1].trim())));
                    }
                    if(aux.contains("+")){
                        String[] aux2 = aux.split("\\+");
                        System.out.println("Resultado: "+(Integer.parseInt(aux2[0].trim())+Integer.parseInt(aux2[1].trim())));
                    }
                }
            }
        } catch (FileNotFoundException f){
            f.printStackTrace();
        }
    }

}
