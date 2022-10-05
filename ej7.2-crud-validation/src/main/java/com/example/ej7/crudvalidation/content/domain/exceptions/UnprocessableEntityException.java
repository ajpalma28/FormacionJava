package com.example.ej7.crudvalidation.content.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.UNPROCESSABLE_ENTITY, reason = "No es procesable supongo")
public class UnprocessableEntityException extends Exception {

    public UnprocessableEntityException(String e){
        super(e);
    }

}
