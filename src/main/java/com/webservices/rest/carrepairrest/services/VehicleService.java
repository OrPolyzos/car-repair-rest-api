package com.webservices.rest.carrepairrest.services;

import com.webservices.rest.carrepairrest.exceptions.user.DuplicateUserException;
import com.webservices.rest.carrepairrest.exceptions.user.UserNotFoundException;
import com.webservices.rest.carrepairrest.exceptions.vehicle.DuplicateVehicleException;
import com.webservices.rest.carrepairrest.exceptions.vehicle.VehicleNotFoundException;
import com.webservices.rest.carrepairrest.model.VehicleModel;

import java.util.List;

public interface VehicleService {

    List<VehicleModel> findAll();

    List<VehicleModel> findByUserID(Long userID) throws UserNotFoundException;

    VehicleModel findByVehicleID(Long vehicleID) throws VehicleNotFoundException;

    VehicleModel save(VehicleModel vehicleModel, Long userID) throws DuplicateVehicleException, UserNotFoundException, DuplicateUserException;
}
