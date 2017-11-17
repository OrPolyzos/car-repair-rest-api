package com.webservices.rest.carrepairrest.controllers;

import com.webservices.rest.carrepairrest.domain.User;
import com.webservices.rest.carrepairrest.exceptions.DuplicateUserException;
import com.webservices.rest.carrepairrest.exceptions.UserIDException;
import com.webservices.rest.carrepairrest.exceptions.UserNotFoundException;
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
        UserModel userModel;
        try {
            userModel = userService.findByUserID(Long.valueOf(userID));
        } catch (NumberFormatException nfex) {
            throw new UserIDException("Invalid User ID!");
        }
        Resource<UserModel> resource = new Resource<>(userModel);
        ControllerLinkBuilder linkTo;
        linkTo = linkTo(methodOn(this.getClass()).getUsers());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }

    @PostMapping("/users")
    public ResponseEntity saveUser(@Validated(UserModel.UserInsert.class) @RequestBody UserModel userModel) throws DuplicateUserException, UserNotFoundException {
        User user = userService.save(userModel);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userID}")
                .buildAndExpand(user.getUserID())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/users")
    public ResponseEntity updateUser(@Validated(UserModel.UserUpdate.class) @RequestBody UserModel userModel) throws DuplicateUserException, UserNotFoundException {
        userService.update(userModel);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/users/{userID}")
    public ResponseEntity deleteUser(@PathVariable String userID) throws UserNotFoundException, UserIDException {
        try {
            userService.deleteByUserID(Long.valueOf(userID));
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (NumberFormatException nfex) {
            throw new UserIDException("Invalid User ID!");
        }
    }
}
