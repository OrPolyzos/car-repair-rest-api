package com.webservices.rest.carrepairrest.exceptions.vehicle;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicateVehicleException extends VehicleException {
    public DuplicateVehicleException() {
        super();
    }

    public DuplicateVehicleException(String msg) {
        super(msg);
    }
}
