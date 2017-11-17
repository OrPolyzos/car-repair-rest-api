package com.webservices.rest.carrepairrest.controllers;

import com.webservices.rest.carrepairrest.domain.User;
import com.webservices.rest.carrepairrest.exceptions.user.DuplicateUserException;
import com.webservices.rest.carrepairrest.exceptions.user.UserIDException;
import com.webservices.rest.carrepairrest.exceptions.user.UserNotFoundException;
import com.webservices.rest.carrepairrest.model.UserModel;
import com.webservices.rest.carrepairrest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserModel> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{userID}")
    public Resource<UserModel> getUser(@PathVariable String userID) throws UserNotFoundException, UserIDException {
        Long userIDL;
        try {
            userIDL = Long.valueOf(userID);
        } catch (NumberFormatException nfex) {
            throw new UserIDException("'" + userID + "' is invalid User ID!");
        }

        UserModel userModel = userService.findByUserID(userIDL);

        Resource<UserModel> resource = new Resource<>(userModel);
        ControllerLinkBuilder linkTo;
        linkTo = linkTo(methodOn(VehiclesController.class).getUserVehicles(userID));
        resource.add(linkTo.withRel("user-vehicles"));
        linkTo = linkTo(methodOn(this.getClass()).getUsers());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }

    @PostMapping("/users")
    public ResponseEntity saveUser(@Valid @RequestBody UserModel userModel) throws DuplicateUserException, UserNotFoundException {
        UserModel savedUserModel = userService.save(userModel);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userID}")
                .buildAndExpand(savedUserModel.getUserID())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/users/{userID}")
    public ResponseEntity updateUser(@Valid @RequestBody UserModel userModel, @PathVariable String userID) throws DuplicateUserException, UserNotFoundException, UserIDException {
        Long userIDL;
        try {
            userIDL = Long.valueOf(userID);
        } catch (NumberFormatException nfex) {
            throw new UserIDException("'" + userID + "' is invalid User ID!");
        }
        userService.update(userModel, userIDL);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/users/{userID}")
    public ResponseEntity deleteUser(@PathVariable String userID) throws UserNotFoundException, UserIDException {
        Long userIDL;
        try {
            userIDL = Long.valueOf(userID);
        } catch (NumberFormatException nfex) {
            throw new UserIDException("'" + userID + "' is invalid User ID!");
        }
        userService.deleteByUserID(userIDL);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
