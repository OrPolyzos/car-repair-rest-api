package com.webservices.rest.carrepairrest.repositories;

import com.webservices.rest.carrepairrest.domain.User;
import com.webservices.rest.carrepairrest.domain.Vehicle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

    List<Vehicle> findAll();

    Optional<Vehicle> findByVehicleID(Long vehicleID);

    List<Vehicle> findByUser(User user);

    Vehicle save(Vehicle vehicle);
}
