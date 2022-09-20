package com.example.ej6.simplecontrollers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Persona {

    @JsonProperty("name")
    public String nombre;

    @JsonProperty("edad")
    public String edad;

    @JsonProperty("ciudad")
    public String ciudad;

}
