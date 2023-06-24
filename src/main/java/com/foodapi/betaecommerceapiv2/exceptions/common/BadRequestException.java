package com.foodapi.betaecommerceapiv2.exceptions.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception {

    private static final Long serialVersionUID = 1L;

    public BadRequestException(String errorMessage){
        super(errorMessage);
    }
}
