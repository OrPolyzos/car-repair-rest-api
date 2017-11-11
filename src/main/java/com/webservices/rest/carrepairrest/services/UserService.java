package com.webservices.rest.carrepairrest.services;

import com.webservices.rest.carrepairrest.domain.User;
import com.webservices.rest.carrepairrest.exceptions.DuplicateUserException;
import com.webservices.rest.carrepairrest.exceptions.UserNotFoundException;
import com.webservices.rest.carrepairrest.model.UserModel;

import java.util.List;

public interface UserService {

    List<UserModel> findAll();

    UserModel findByUserID(Long userID) throws UserNotFoundException;

    User save(UserModel userModel) throws DuplicateUserException;

    User update(UserModel userModel) throws UserNotFoundException, DuplicateUserException;

    void deleteByUserID(Long userID) throws UserNotFoundException;
}
