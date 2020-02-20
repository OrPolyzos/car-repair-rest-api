package com.webservices.rest.carrepairrest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor @Data
public class VehicleModel {

    @Null(message = "VehicleID should be null!")
    private Long vehicleID;

    @NotNull(message = "PlateNumber is required!")
    @Pattern(regexp = "^[A-Z]{3}-[0-9]{4}", message = "Plate number should be of the format 'ABC-1234'!")
    private String plateNumber;

    @NotNull(message = "Brand is required!")
    @Size(max = 32, message = "Brand should be up to 32 characters!")
    @Pattern(regexp = "^[0-9a-zA-Z]{1,32}", message = "Brand should contain alphanumericals!")
    private String brand;

    @NotNull(message = "Model is required!")
    @Size(max = 32, message = "Model should be up to 32 characters!")
    @Pattern(regexp = "^[0-9a-zA-Z]{1,32}", message = "Model should contain only alphanumericals!")
    private String model;

    @JsonIgnore
    private UserModel userModel;

    public VehicleModel(Long vehicleID, String plateNumber, String brand, String model, UserModel userModel) {
        this.vehicleID = vehicleID;
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.model = model;
        this.userModel = userModel;
    }

}
