package com.webservices.rest.carrepairrest.converters;

import com.webservices.rest.carrepairrest.domain.User;
import com.webservices.rest.carrepairrest.model.UserModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConverter extends Mappable<User, UserModel> {
}
