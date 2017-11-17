package com.webservices.rest.carrepairrest.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserIDException extends UserException {

    public UserIDException() {
        super();
    }

    public UserIDException(String msg) {
        super(msg);
    }

}
