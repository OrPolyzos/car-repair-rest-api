package com.webservices.rest.carrepairrest.domain;

import javax.persistence.*;

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

    public Vehicle() {}

    public Vehicle(Long vehicleID, String plateNumber, String brand, String model, User user) {
        this.vehicleID = vehicleID;
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.model = model;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
