package com.example.ej7.crud.content.asignacion.domain;

public class PersonaImpl implements Persona {

    private String nombre;
    private Integer edad;
    private String poblacion;

    public PersonaImpl(String n, Integer e, String p){
        nombre = n;
        edad = e;
        poblacion = p;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public Integer getEdad() {
        return edad;
    }

    @Override
    public String getPoblacion() {
        return poblacion;
    }

    @Override
    public void setNombre(String s) {
        nombre = s;
    }

    @Override
    public void setEdad(Integer e) {
        edad = e;
    }

    @Override
    public void setPoblacion(String p) {
        poblacion = p;
    }

    public String toString(){
        return "Nombre: "+nombre+"; Edad: "+edad+"; Ciudad: "+poblacion+".";
    }
}
