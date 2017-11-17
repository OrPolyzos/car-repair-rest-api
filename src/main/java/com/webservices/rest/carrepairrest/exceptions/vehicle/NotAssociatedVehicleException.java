package com.webservices.rest.carrepairrest.exceptions.vehicle;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotAssociatedVehicleException extends VehicleException {
    public NotAssociatedVehicleException() {
        super();
    }

    public NotAssociatedVehicleException(String msg) {
        super(msg);
    }
}
