package com.webservices.rest.carrepairrest.repositories;

import com.webservices.rest.carrepairrest.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {

    List<User> findAll();

    Optional<User> findByUserID(Long userID);

    User save(User user);

    void deleteByUserID(Long userID);

}
