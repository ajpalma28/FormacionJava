package com.example.ej6.personcontrollers.objetos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CiudadImpl implements Ciudad {

    @JsonProperty("nombre")
    private String nombre;
    private int numeroHabitantes;

    public CiudadImpl(@JsonProperty("nombre") String nombre){
        this.nombre=nombre;
        numeroHabitantes=0;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public int getNumeroHabitantes() {
        return numeroHabitantes;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre=nombre;
    }

    @Override
    public void setNumeroHabitantes() {
        this.numeroHabitantes++;
    }

    @Override
    public boolean equals(Object obj) {
        Ciudad c = (Ciudad) obj;
        return this.getNombre().equals(c.getNombre());
    }

    public String toString(){
        return "Nombre: "+nombre+" ("+numeroHabitantes+" habitantes).";
    }
}
