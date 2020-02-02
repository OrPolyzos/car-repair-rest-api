package com.webservices.rest.carrepairrest.converters;

import com.webservices.rest.carrepairrest.domain.Vehicle;
import com.webservices.rest.carrepairrest.model.VehicleModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleConverter extends  Mappable<Vehicle, VehicleModel> {
}
