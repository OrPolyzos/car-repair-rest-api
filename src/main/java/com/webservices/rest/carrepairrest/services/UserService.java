package com.webservices.rest.carrepairrest.services;

import com.webservices.rest.carrepairrest.domain.User;
import com.webservices.rest.carrepairrest.exceptions.user.DuplicateUserException;
import com.webservices.rest.carrepairrest.exceptions.user.UserNotFoundException;
import com.webservices.rest.carrepairrest.model.UserModel;

import java.util.List;

public interface UserService {

    List<UserModel> findAll();

    UserModel findByUserID(Long userID) throws UserNotFoundException;

    UserModel save(UserModel userModel) throws DuplicateUserException;

    UserModel update(UserModel userModel) throws UserNotFoundException, DuplicateUserException;

    void deleteByUserID(Long userID) throws UserNotFoundException;
}
