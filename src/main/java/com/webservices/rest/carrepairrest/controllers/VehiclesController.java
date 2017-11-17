package com.webservices.rest.carrepairrest.controllers;

import com.webservices.rest.carrepairrest.exceptions.user.DuplicateUserException;
import com.webservices.rest.carrepairrest.exceptions.user.UserIDException;
import com.webservices.rest.carrepairrest.exceptions.user.UserNotFoundException;
import com.webservices.rest.carrepairrest.exceptions.vehicle.DuplicateVehicleException;
import com.webservices.rest.carrepairrest.exceptions.vehicle.NotAssociatedVehicleException;
import com.webservices.rest.carrepairrest.exceptions.vehicle.VehicleIDException;
import com.webservices.rest.carrepairrest.exceptions.vehicle.VehicleNotFoundException;
import com.webservices.rest.carrepairrest.model.VehicleModel;
import com.webservices.rest.carrepairrest.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
        Long vehicleIDL;
        try {
            vehicleIDL = Long.valueOf(vehicleID);
        } catch (NumberFormatException nfex) {
            throw new VehicleIDException("Invalid Vehicle ID!");
        }

        VehicleModel vehicleModel = vehicleService.findByVehicleID(vehicleIDL);

        Resource<VehicleModel> resource = new Resource<>(vehicleModel);
        ControllerLinkBuilder linkTo;
        linkTo = linkTo(methodOn(this.getClass()).getVehicles());
        resource.add(linkTo.withRel("all-vehicles"));
        return resource;
    }

    @GetMapping("/users/{userID}/vehicles")
    public List<VehicleModel> getUserVehicles(@PathVariable String userID) throws UserIDException, UserNotFoundException {
        Long userIDL;
        try {
            userIDL = Long.valueOf(userID);
        } catch (NumberFormatException nfex) {
            throw new UserIDException("'" + userID + "' is invalid User ID!");
        }
        return vehicleService.findByUserID(userIDL);
    }

    @GetMapping("/users/{userID}/vehicles/{vehicleID}")
    public VehicleModel getUserVehicle(@PathVariable String userID, @PathVariable String vehicleID) throws UserIDException, UserNotFoundException, VehicleIDException, NotAssociatedVehicleException {
        Long userIDL;
        try {
            userIDL = Long.valueOf(userID);
        } catch (NumberFormatException nfex) {
            throw new UserIDException("'" + userID + "' is invalid User ID!");
        }
        Long vehicleIDL;
        try {
            vehicleIDL = Long.valueOf(vehicleID);
        } catch (NumberFormatException nfex) {
            throw new VehicleIDException("Invalid Vehicle ID!");
        }
        return vehicleService.findByVehicleIDAndUserID(vehicleIDL, userIDL);
    }

    @PostMapping("/users/{userID}/vehicles")
    public ResponseEntity saveVehicle(@Valid @RequestBody VehicleModel vehicleModel,
                                      @PathVariable String userID) throws DuplicateUserException, UserNotFoundException, UserIDException, DuplicateVehicleException {
        Long userIDL;
        try {
            userIDL = Long.valueOf(userID);
        } catch (NumberFormatException nfex) {
            throw new UserIDException("'" + userID + "' is invalid User ID!");
        }

        VehicleModel savedVehicleModel = vehicleService.save(vehicleModel, userIDL);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{vehicleID}")
                .buildAndExpand(savedVehicleModel.getVehicleID())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/users/{userID}/vehicles/{vehicleID}")
    public ResponseEntity updateVehicle(@Valid @RequestBody VehicleModel vehicleModel,
                                        @PathVariable String userID, @PathVariable String vehicleID) throws UserIDException, VehicleIDException, UserNotFoundException, DuplicateVehicleException, DuplicateUserException, NotAssociatedVehicleException {
        Long userIDL;
        try {
            userIDL = Long.valueOf(userID);
        } catch (NumberFormatException nfex) {
            throw new UserIDException("'" + userID + "' is invalid User ID!");
        }
        Long vehicleIDL;
        try {
            vehicleIDL = Long.valueOf(vehicleID);
        } catch (NumberFormatException nfex) {
            throw new VehicleIDException("Invalid Vehicle ID!");
        }
        vehicleService.update(vehicleModel, userIDL, vehicleIDL);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
