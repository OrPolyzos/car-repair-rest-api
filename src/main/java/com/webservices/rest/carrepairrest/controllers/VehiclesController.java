package com.webservices.rest.carrepairrest.controllers;

import com.webservices.rest.carrepairrest.exceptions.user.DuplicateUserException;
import com.webservices.rest.carrepairrest.exceptions.user.UserIDException;
import com.webservices.rest.carrepairrest.exceptions.user.UserNotFoundException;
import com.webservices.rest.carrepairrest.exceptions.vehicle.DuplicateVehicleException;
import com.webservices.rest.carrepairrest.exceptions.vehicle.VehicleIDException;
import com.webservices.rest.carrepairrest.exceptions.vehicle.VehicleNotFoundException;
import com.webservices.rest.carrepairrest.model.VehicleModel;
import com.webservices.rest.carrepairrest.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping("/vehicles/{vehicleID}")
    public Resource<VehicleModel> getVehicle(@PathVariable String vehicleID) throws VehicleNotFoundException, VehicleIDException {
        VehicleModel vehicleModel;
        try {
            vehicleModel = vehicleService.findByVehicleID(Long.valueOf(vehicleID));
        } catch (NumberFormatException nfex) {
            throw new VehicleIDException("Invalid Vehicle ID!");
        }
        Resource<VehicleModel> resource = new Resource<>(vehicleModel);
        ControllerLinkBuilder linkTo;
        linkTo = linkTo(methodOn(this.getClass()).getVehicles());
        resource.add(linkTo.withRel("all-vehicles"));
        return resource;
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

    @PostMapping("/users/{userID}/vehicles")
    public ResponseEntity saveVehicle(@Validated(VehicleModel.VehicleInsert.class) @RequestBody VehicleModel vehicleModel,
                                      @PathVariable String userID) throws DuplicateUserException, UserNotFoundException, UserIDException, DuplicateVehicleException {
        VehicleModel savedVehicleModel;
        try {
            savedVehicleModel = vehicleService.save(vehicleModel, Long.valueOf(userID));
        } catch (NumberFormatException nfex) {
            throw new UserIDException("Invalid User ID!");
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{vehicleID}")
                .buildAndExpand(savedVehicleModel.getVehicleID())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
