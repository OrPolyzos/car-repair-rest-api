package com.webservices.rest.carrepairrest.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException  extends UserException{
    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
