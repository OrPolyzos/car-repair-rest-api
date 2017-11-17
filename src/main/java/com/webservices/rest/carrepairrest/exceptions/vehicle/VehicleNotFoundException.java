package com.webservices.rest.carrepairrest.exceptions.vehicle;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class VehicleNotFoundException extends VehicleException {
    public VehicleNotFoundException() {
        super();
    }

    public VehicleNotFoundException(String msg) {
        super(msg);
    }
}
