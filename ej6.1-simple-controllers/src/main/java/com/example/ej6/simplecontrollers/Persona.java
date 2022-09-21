package com.example.ej6.simplecontrollers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Persona {

    @JsonProperty("name")
    public String nombre;

    @JsonProperty("edad")
    public String edad;

    @JsonProperty("ciudad")
    public String ciudad;

    public Persona(@JsonProperty("name") String n, @JsonProperty("edad") String e, @JsonProperty("ciudad") String c){
        nombre = n;
        edad = e;
        ciudad = c;
    }

}
