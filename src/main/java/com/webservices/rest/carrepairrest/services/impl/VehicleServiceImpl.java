package com.webservices.rest.carrepairrest.services.impl;

import com.webservices.rest.carrepairrest.converters.UserConverter;
import com.webservices.rest.carrepairrest.converters.VehicleConverter;
import com.webservices.rest.carrepairrest.domain.User;
import com.webservices.rest.carrepairrest.domain.Vehicle;
import com.webservices.rest.carrepairrest.exceptions.user.UserNotFoundException;
import com.webservices.rest.carrepairrest.exceptions.vehicle.DuplicateVehicleException;
import com.webservices.rest.carrepairrest.exceptions.vehicle.NotAssociatedVehicleException;
import com.webservices.rest.carrepairrest.exceptions.vehicle.VehicleNotFoundException;
import com.webservices.rest.carrepairrest.model.UserModel;
import com.webservices.rest.carrepairrest.model.VehicleModel;
import com.webservices.rest.carrepairrest.repositories.VehicleRepository;
import com.webservices.rest.carrepairrest.services.UserService;
import com.webservices.rest.carrepairrest.services.VehicleService;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    final private VehicleRepository vehicleRepository;

    final private UserService userService;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, UserService userService){
        this.vehicleRepository = vehicleRepository;
        this.userService = userService;
    }

    @Override
    public List<VehicleModel> findAll() {
        return vehicleRepository.findAll()
                .stream()
                .map(VehicleConverter::convertToVehicleModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleModel> findByUserID(Long userID) throws UserNotFoundException {
        return vehicleRepository.findByUser(UserConverter.convertToUser(userService.findByUserID(userID)))
                .stream()
                .map(VehicleConverter::convertToVehicleModel)
                .collect(Collectors.toList());
    }

    @Override
    public VehicleModel findByVehicleID(Long vehicleID) throws VehicleNotFoundException {
        Optional<Vehicle> vehicle = vehicleRepository.findByVehicleID(vehicleID);
        if (vehicle.isPresent()) {
            return VehicleConverter.convertToVehicleModel(vehicle.get());
        } else {
            throw new VehicleNotFoundException("Vehicle with VehicleID: '" + vehicleID + "' was not found!");
        }
    }

    @Override
    public VehicleModel findByVehicleIDAndUserID(Long vehicleID, Long userID) throws UserNotFoundException, NotAssociatedVehicleException {
        User retrievedUser = UserConverter.convertToUser(userService.findByUserID(userID));
        Optional<Vehicle> retrievedVehicle = vehicleRepository.findByVehicleIDAndUser(vehicleID, retrievedUser);
        if (!retrievedVehicle.isPresent()) {
            throw new NotAssociatedVehicleException("The user with User ID: '" + userID +
                    "' does not own any vehicle with Vehicle ID: '" + vehicleID + "'!");
        }
        return VehicleConverter.convertToVehicleModel(retrievedVehicle.get());
    }

    @Override
    public VehicleModel save(VehicleModel vehicleModel, Long userID) throws DuplicateVehicleException, UserNotFoundException {
        UserModel retrievedUserModel = userService.findByUserID(userID);
        vehicleModel.setUserModel(retrievedUserModel);
        try {
            return VehicleConverter.convertToVehicleModel(vehicleRepository.save(VehicleConverter.convertToVehicle(vehicleModel)));
        } catch (DataIntegrityViolationException divex) {
            throw new DuplicateVehicleException("Vehicle with Plate Number: '" + vehicleModel.getPlateNumber() + "', exists already!");
        }
    }

    @Override
    public VehicleModel update(VehicleModel vehicleModel, Long userID, Long vehicleID) throws NotAssociatedVehicleException, UserNotFoundException, DuplicateVehicleException {
        findByVehicleIDAndUserID(vehicleID, userID);
        vehicleModel.setVehicleID(vehicleID);
        return save(vehicleModel, userID);
    }
}
