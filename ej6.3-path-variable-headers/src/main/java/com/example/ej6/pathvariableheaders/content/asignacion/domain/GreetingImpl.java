package com.example.ej6.pathvariableheaders.content.asignacion.domain;

import com.example.ej6.pathvariableheaders.content.asignacion.domain.Greeting;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GreetingImpl implements Greeting {

    private long id;
    private String content;

    public GreetingImpl(@JsonProperty("id") long idNew, @JsonProperty("content") String contentNew){
        id = idNew;
        content = contentNew;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getContent() {
        return content;
    }

    public void setContent(String c){
        content = c;
    }
}
