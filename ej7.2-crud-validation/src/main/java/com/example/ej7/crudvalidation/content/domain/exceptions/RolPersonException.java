package com.example.ej7.crudvalidation.content.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Esta persona ya tiene otro rol registrado")
public class RolPersonException extends Exception {

    public RolPersonException(String e){
        super(e);
    }

}
