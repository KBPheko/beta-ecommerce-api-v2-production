package com.foodapi.betaecommerceapiv2.exceptions.order;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends Exception{

    private static final long serialVersionUID = 1L;

    public OrderNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
