package com.webservices.rest.carrepairrest.services;

import com.webservices.rest.carrepairrest.converters.UserConverter;
import com.webservices.rest.carrepairrest.converters.VehicleConverter;
import com.webservices.rest.carrepairrest.exceptions.UserNotFoundException;
import com.webservices.rest.carrepairrest.model.VehicleModel;
import com.webservices.rest.carrepairrest.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
