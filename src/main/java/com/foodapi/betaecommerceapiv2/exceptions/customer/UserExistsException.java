package com.foodapi.betaecommerceapiv2.exceptions.customer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserExistsException extends Exception {
    private static final long serialVersionUID = 1L;

    public UserExistsException(String errorMessage) {
        super(errorMessage);
    }

}
