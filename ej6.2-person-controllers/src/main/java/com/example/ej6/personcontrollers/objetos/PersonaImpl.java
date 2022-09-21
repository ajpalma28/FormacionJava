package com.example.ej6.personcontrollers.objetos;

import com.example.ej6.personcontrollers.objetos.Persona;

import java.util.Objects;

public class PersonaImpl implements Persona {

    public String nombre, poblacion;
    public Integer edad;

    public PersonaImpl(String n, String p, Integer e){
        nombre = n;
        poblacion = p;
        edad = e;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String getPoblacion() {
        return poblacion;
    }

    @Override
    public Integer getEdad() {
        return edad;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    @Override
    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String toString(){
        return "Nombre: "+nombre+"; Edad: "+edad+"; Ciudad: "+poblacion+".";
    }

    public boolean equals(Object o) {
        Persona p1 = (Persona) o;
        return nombre.equals(p1.getNombre()) && poblacion.equals(p1.getPoblacion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre,poblacion);
    }
}
