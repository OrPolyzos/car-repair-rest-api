package com.webservices.rest.carrepairrest.converters;

import com.webservices.rest.carrepairrest.domain.User;
import com.webservices.rest.carrepairrest.model.UserModel;

public class UserConverter {

    public static UserModel convertToUserModel(User user){
        return new UserModel(user.getUserID(),user.getFirstName(),user.getLastName(),user.getEmail(),user.getSsn());
    }

    public static User convertToUser(UserModel userModel){
        return new User(userModel.getUserID(),userModel.getFirstName(),userModel.getLastName(),userModel.getEmail(),userModel.getSsn());
    }
}
