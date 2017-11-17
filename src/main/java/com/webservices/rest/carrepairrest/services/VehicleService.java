package com.webservices.rest.carrepairrest.services;

import com.webservices.rest.carrepairrest.exceptions.UserNotFoundException;
import com.webservices.rest.carrepairrest.model.VehicleModel;

import java.util.List;

public interface VehicleService {

    List<VehicleModel> findAll();

    List<VehicleModel> findByUserID(Long userID) throws UserNotFoundException;
}
