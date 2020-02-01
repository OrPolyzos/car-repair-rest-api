package com.webservices.rest.carrepairrest.services.impl;

import com.webservices.rest.carrepairrest.converters.UserConverter;
import com.webservices.rest.carrepairrest.domain.User;
import com.webservices.rest.carrepairrest.exceptions.user.DuplicateUserException;
import com.webservices.rest.carrepairrest.exceptions.user.UserNotFoundException;
import com.webservices.rest.carrepairrest.model.UserModel;
import com.webservices.rest.carrepairrest.repositories.UserRepository;
import com.webservices.rest.carrepairrest.services.UserService;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<UserModel> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserConverter::convertToUserModel)
                .collect(Collectors.toList());
    }

    @Override
    public UserModel findByUserID(Long userID) throws UserNotFoundException {
        Optional<User> user = userRepository.findByUserID(userID);
        if (user.isPresent()) {
            return UserConverter.convertToUserModel(user.get());
        } else {
            throw new UserNotFoundException("User with UserID: '" + userID + "' was not found!");
        }
    }

    @Override
    public UserModel save(UserModel userModel) throws DuplicateUserException {
        try {
            return UserConverter.convertToUserModel(userRepository.save(UserConverter.convertToUser(userModel)));
        } catch (DataIntegrityViolationException divex) {
            throw new DuplicateUserException("User with Email: '" + userModel.getEmail()
                    + "' or with SSN: '" + userModel.getSsn() + "', exists already!");
        }
    }

    @Override
    public UserModel update(UserModel userModel, Long userID) throws UserNotFoundException, DuplicateUserException {
        findByUserID(userID);
        userModel.setUserID(userID);
        return save(userModel);
    }

    @Override
    @Transactional
    public void deleteByUserID(Long userID) throws UserNotFoundException {
        findByUserID(userID);
        userRepository.deleteByUserID(userID);
    }

}
