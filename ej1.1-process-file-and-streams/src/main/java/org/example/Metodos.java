package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Metodos {

    static List<Person> lista = new ArrayList<>();

    public static void lecturaFichero1(String ubicacion) {
        try {
            Scanner scanner = new Scanner(new File(ubicacion));
            scanner.useDelimiter("\r\n");
            while (scanner.hasNext()) {
                String aux = scanner.next();
                if(aux.startsWith(":")) {
                    throw new InvalidLineFormatException("El nombre es obligatorio");
                }
                if(contarDosPuntos(aux)!=2){
                    throw new InvalidLineFormatException("Falta un delimitador de campo (:)");
                }else if(contarDosPuntos(aux)==0){
                    throw new InvalidLineFormatException("Faltan dos delimitadores de campo (:)");
                }
                aux.replace("::", ":-:");
                String[] persona = aux.split(":");
                if (persona.length == 3) {
                    Person p = new PersonImpl(persona[0].trim().replace("-", ""),
                            persona[1].trim().replace("-", ""),
                            persona[2]);
                    lista.add(p);
                } else {
                    Person p = new PersonImpl(persona[0].trim().replace("-", ""),
                            persona[1].trim().replace("-", ""), "0");
                    lista.add(p);
                }
            }
            devuelveLista(lista);
            // A
            System.out.println("\nEjercicio a)");
            lista.stream().filter(x->x.getAge()<25).forEach(x->{
                String imprime = "";
                if(x.getTown()==""){
                    imprime = "Name: "+x.getName()+". Town: unknown";
                }else{
                    imprime = "Name: "+x.getName()+". Town: "+x.getTown();
                }
                if(x.getAge()==0){
                    System.out.println(imprime+". Age: unknown");
                }else{
                    System.out.println(imprime+". Age: "+x.getAge());
                }
            });

            // B
            System.out.println("\nEjercicio b)");
            lista.stream().filter(x->!x.getName().startsWith("A")).forEach(x->{
                String imprime = "";
                if(x.getTown()==""){
                    imprime = "Name: "+x.getName()+". Town: unknown";
                }else{
                    imprime = "Name: "+x.getName()+". Town: "+x.getTown();
                }
                if(x.getAge()==0){
                    System.out.println(imprime+". Age: unknown");
                }else{
                    System.out.println(imprime+". Age: "+x.getAge());
                }
            });

            // C
            System.out.println("\nEjercicio c)");
            lista.stream().filter(x->x.getAge()<25 && x.getTown().equals("Madrid")).findFirst().stream().forEach(x->{
                String imprime = "";
                if(x.getTown()==""){
                    imprime = "Name: "+x.getName()+". Town: unknown";
                }else{
                    imprime = "Name: "+x.getName()+". Town: "+x.getTown();
                }
                if(x.getAge()==0){
                    System.out.println(imprime+". Age: unknown");
                }else{
                    System.out.println(imprime+". Age: "+x.getAge());
                }
            });

            // D
            System.out.println("\nEjercicio d)");
            lista.stream().filter(x->x.getAge()<25 && x.getTown().equals("Barcelona")).findFirst().stream().forEach(x->{
                String imprime = "";
                if(x.getTown()==""){
                    imprime = "Name: "+x.getName()+". Town: unknown";
                }else{
                    imprime = "Name: "+x.getName()+". Town: "+x.getTown();
                }
                if(x.getAge()==0){
                    System.out.println(imprime+". Age: unknown");
                }else{
                    System.out.println(imprime+". Age: "+x.getAge());
                }
            });
        } catch (FileNotFoundException f){
            f.printStackTrace();
        } catch (InvalidLineFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer contarDosPuntos(String s){
        Integer cont = 0;
        Integer pos = 0;
        pos = s.indexOf(":");
        while (pos != -1){
            cont++;
            pos = s.indexOf(":",pos+1);
        }
        return cont;
    }

    public static void devuelveLista(List<Person> l){
        l.forEach(x->System.out.println(x.toString()));
    }

}
