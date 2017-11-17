package com.webservices.rest.carrepairrest.controllers;

import com.webservices.rest.carrepairrest.exceptions.UserIDException;
import com.webservices.rest.carrepairrest.exceptions.UserNotFoundException;
import com.webservices.rest.carrepairrest.model.UserModel;
import com.webservices.rest.carrepairrest.model.VehicleModel;
import com.webservices.rest.carrepairrest.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class VehiclesController {

    @Autowired
    private UsersController usersController;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/vehicles")
    public List<VehicleModel> getVehicles() {
        return vehicleService.findAll();
    }

    @GetMapping("/users/{userID}/vehicles")
    public List<VehicleModel> getUserVehicles(@PathVariable String userID) throws UserIDException, UserNotFoundException {
        List<VehicleModel> vehiclesList;
        try {
            vehiclesList = vehicleService.findByUserID(Long.valueOf(userID));
            return vehiclesList;
        } catch (NumberFormatException nfex) {
            throw new UserIDException("Invalid User ID!");
        }
    }
}
