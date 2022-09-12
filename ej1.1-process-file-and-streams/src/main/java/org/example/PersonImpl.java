package org.example;

import java.util.Objects;
import java.util.Optional;

public class PersonImpl implements Person {

    String name;
    String town;
    Integer age;

    public PersonImpl(String name, String town, String age){
        this.name = name;
        this.town=town;
        if(age.isEmpty()){
            this.age = 0;
        }else{
            this.age = Integer.parseInt(age);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTown() {
        return town;
    }

    @Override
    public Integer getAge() {
        return age;
    }

    @Override
    public void setNombre(String n) {
        this.name=n;
    }

    @Override
    public void setTown(String t) {
        this.town=t;
    }

    @Override
    public void setAge(Integer a) {
        this.age=a;
    }

    public String toString(){
        return "Name: "+name+". Town: "+town+". Age: "+age;
    }
}
