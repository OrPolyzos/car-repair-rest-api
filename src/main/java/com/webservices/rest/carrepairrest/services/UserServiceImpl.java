package com.webservices.rest.carrepairrest.services;

import com.webservices.rest.carrepairrest.converters.UserConverter;
import com.webservices.rest.carrepairrest.domain.User;
import com.webservices.rest.carrepairrest.exceptions.DuplicateUserException;
import com.webservices.rest.carrepairrest.exceptions.UserNotFoundException;
import com.webservices.rest.carrepairrest.model.UserModel;
import com.webservices.rest.carrepairrest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserConverter::convertToUserModel)
                .collect(Collectors.toList());
    }

    public UserModel findByUserID(Long userID) throws UserNotFoundException {
        Optional<User> user = userRepository.findByUserID(userID);
        if (user.isPresent()) {
            return UserConverter.convertToUserModel(user.get());
        } else {
            throw new UserNotFoundException("User with UserID:" + userID + " was not found!");
        }
    }

    public User save(UserModel userModel) throws DuplicateUserException {
        try {
            return userRepository.save(UserConverter.convertToUser(userModel));
        } catch (DataIntegrityViolationException divex) {
            throw new DuplicateUserException("User with Email: " + userModel.getEmail()
                    + " or with SSN: " + userModel.getSsn() + " exists already!");
        }
    }

    public User update(UserModel userModel) throws UserNotFoundException, DuplicateUserException {
        findByUserID(userModel.getUserID());
        return save(userModel);
    }

    @Transactional
    public void deleteByUserID(Long userID) throws UserNotFoundException {
        findByUserID(userID);
        userRepository.deleteByUserID(userID);
    }

}
