package com.webservices.rest.carrepairrest.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

@Data @NoArgsConstructor
@Entity(name = "Users")
public class User {

    @Id
    @Column(name = "UserID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column(name = "FirstName", nullable = false)
    private String firstName;

    @Column(name = "LastName", nullable = false)
    private String lastName;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "SSN", nullable = false, unique = true)
    private String ssn;

    @OneToMany(mappedBy="user")
    private List<Vehicle> vehicleList;

    public User(Long userID, String firstName, String lastName, String email, String ssn) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.ssn = ssn;
    }
}
