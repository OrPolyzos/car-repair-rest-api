package com.webservices.rest.carrepairrest.exceptions.vehicle;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class VehicleIDException extends VehicleException {

    public VehicleIDException() {
        super();
    }

    public VehicleIDException(String msg) {
        super(msg);
    }

}
