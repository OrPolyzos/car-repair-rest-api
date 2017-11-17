package com.webservices.rest.carrepairrest.converters;

import com.webservices.rest.carrepairrest.domain.Vehicle;
import com.webservices.rest.carrepairrest.model.VehicleModel;

public class VehicleConverter {

    public static VehicleModel convertToVehicleModel(Vehicle vehicle) {
        return new VehicleModel(vehicle.getVehicleID(), vehicle.getPlateNumber(), vehicle.getBrand(), vehicle.getModel());
    }

    public static Vehicle convertToVehicle(VehicleModel vehicleModel) {
        return new Vehicle(vehicleModel.getVehicleID(), vehicleModel.getPlateNumber(), vehicleModel.getBrand(), vehicleModel.getModel());
    }
}
