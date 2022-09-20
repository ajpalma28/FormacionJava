package com.example.ej6.simplecontrollers;

public class PersonImpl implements Person {

    String name, ciudad;
    Integer edad;

    public PersonImpl(String n, Integer e, String c){
        name=n;
        edad=e;
        ciudad=c;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getEdad() {
        return edad;
    }

    @Override
    public String getCiudad() {
        return ciudad;
    }

    @Override
    public void setName(String n) {
        name = n;
    }

    @Override
    public void setEdad(Integer n) {
        edad = n;
    }

    @Override
    public void setCiudad(String n) {
        ciudad = n;
    }
}
