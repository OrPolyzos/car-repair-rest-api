package com.webservices.rest.carrepairrest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid User ID (path variable)")
public class UserIDException extends UserException {

    public UserIDException() {
        super();
    }

    public UserIDException(String msg) {
        super(msg);
    }

}
