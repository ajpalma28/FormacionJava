package com.example.ej7.crudvalidation.content.domain;

import java.time.Instant;
import java.util.Date;

public class CustomError {

    private Date timestamp;
    private int HttpCode;
    private String mensaje;

    public CustomError(int codigo, String msj){
        timestamp = Date.from(Instant.now());
        HttpCode = codigo;
        mensaje = msj;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int getHttpCode() {
        return HttpCode;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setHttpCode(int httpCode) {
        HttpCode = httpCode;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String toString(){
        return timestamp+" -> "+HttpCode+": "+mensaje;
    }

}
