package com.webservices.rest.carrepairrest.services;

import com.webservices.rest.carrepairrest.converters.UserConverter;
import com.webservices.rest.carrepairrest.converters.VehicleConverter;
import com.webservices.rest.carrepairrest.domain.User;
import com.webservices.rest.carrepairrest.domain.Vehicle;
import com.webservices.rest.carrepairrest.exceptions.user.DuplicateUserException;
import com.webservices.rest.carrepairrest.exceptions.user.UserNotFoundException;
import com.webservices.rest.carrepairrest.exceptions.vehicle.DuplicateVehicleException;
import com.webservices.rest.carrepairrest.exceptions.vehicle.VehicleNotFoundException;
import com.webservices.rest.carrepairrest.model.UserModel;
import com.webservices.rest.carrepairrest.model.VehicleModel;
import com.webservices.rest.carrepairrest.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserService userService;

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
    public VehicleModel save(VehicleModel vehicleModel, Long userID) throws DuplicateVehicleException, UserNotFoundException, DuplicateUserException {
        UserModel retrievedUserModel = userService.findByUserID(userID);
        vehicleModel.setUserModel(retrievedUserModel);
        try {
            return VehicleConverter.convertToVehicleModel(vehicleRepository.save(VehicleConverter.convertToVehicle(vehicleModel)));
        } catch (DataIntegrityViolationException divex) {
            throw new DuplicateVehicleException("Vehicle with Plate Number: '" + vehicleModel.getPlateNumber() + "', exists already!");
        }
    }
}
