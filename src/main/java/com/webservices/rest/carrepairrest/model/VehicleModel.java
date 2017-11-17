package com.webservices.rest.carrepairrest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class VehicleModel {

    public interface VehicleInsert {
    }

    public interface VehicleUpdate {
    }

    @Null(groups = {VehicleInsert.class}, message = "VehicleID should be null when you create a new vehicle!")
    @NotNull(groups = {VehicleUpdate.class}, message = "VehicleID should not be null when updating a vehicle!")
    private Long vehicleID;

    @NotNull(groups = {VehicleInsert.class, VehicleUpdate.class}, message = "PlateNumber is required!")
    @Pattern(groups = {VehicleInsert.class, VehicleUpdate.class}, regexp = "^[A-Z]{3}-[0-9]{4}", message = "Plate number should be of the format 'ABC-1234'!")
    private String plateNumber;

    @NotNull(groups = {VehicleInsert.class, VehicleUpdate.class}, message = "Brand is required!")
    @Size(groups = {VehicleInsert.class, VehicleUpdate.class}, max = 32, message = "Brand should be up to 32 characters!")
    @Pattern(groups = {VehicleInsert.class, VehicleUpdate.class}, regexp = "^[0-9a-zA-Z]{1,32}", message = "Brand should contain alphanumericals!")
    private String brand;

    @NotNull(groups = {VehicleInsert.class, VehicleUpdate.class}, message = "Model is required!")
    @Size(groups = {VehicleInsert.class, VehicleUpdate.class}, max = 32, message = "Model should be up to 32 characters!")
    @Pattern(groups = {VehicleInsert.class, VehicleUpdate.class}, regexp = "^[0-9a-zA-Z]{1,32}", message = "Model should contain only alphanumericals!")
    private String model;

    @JsonIgnore
    private UserModel userModel;

    public VehicleModel() {
    }

    public VehicleModel(Long vehicleID, String plateNumber, String brand, String model, UserModel userModel) {
        this.vehicleID = vehicleID;
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.model = model;
        this.userModel = userModel;
    }

    public Long getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(Long vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
