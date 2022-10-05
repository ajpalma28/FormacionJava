package com.example.ej7.crudvalidation.content.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "ID no encontrado")
public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(String e){
        super(e);
    }

}
