package com.webservices.rest.carrepairrest.repositories;

import com.webservices.rest.carrepairrest.domain.User;
import com.webservices.rest.carrepairrest.domain.Vehicle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

    List<Vehicle> findAll();

    List<Vehicle> findByUser(User user);
}
