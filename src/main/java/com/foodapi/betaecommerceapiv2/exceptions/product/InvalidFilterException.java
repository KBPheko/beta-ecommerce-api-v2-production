package com.foodapi.betaecommerceapiv2.exceptions.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidFilterException extends Exception{

    private static final long serialVersionUID = 1L;

    public InvalidFilterException(String errorMessage) {
        super(errorMessage);
    }
}
