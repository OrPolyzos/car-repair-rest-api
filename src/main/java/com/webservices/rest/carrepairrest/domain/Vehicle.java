package com.webservices.rest.carrepairrest.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "Vehicles")
public class Vehicle {

    @Id
    @Column(name = "VehicleID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleID;

    @Column(name = "PlateNumber", nullable = false, unique = true)
    private String plateNumber;

    @Column(name = "Brand", nullable = false)
    private String brand;

    @Column(name = "Model", nullable = false)
    private String model;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Vehicle(Long vehicleID, String plateNumber, String brand, String model, User user) {
        this.vehicleID = vehicleID;
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.model = model;
        this.user = user;
    }

}
