package com.webservices.rest.carrepairrest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@NoArgsConstructor @Data
public class UserModel {

    @Null(message = "UserID should be null when you create a new user!")
    @NotNull(message = "UserID should not be null when updating a user!")
    private Long userID;

    @NotNull(message = "First Name is required!")
    @Size(min = 1, max = 128, message = "Maximum length is 128 characters!")
    @Pattern(regexp = "^[a-zA-Z]{1,128}", message = "First Name should contain only letters!")
    private String firstName;

    @NotNull(message = "Last Name is required!")
    @Size(min = 1, max = 128, message = "Maximum length is 128 characters!")
    @Pattern(regexp = "^[a-zA-Z]{1,128}", message = "Last Name should contain only letters!")
    private String lastName;

    @NotNull(message = "Email is required!")
    @Size(min = 1, max = 128, message = "Maximum length is 128 characters!")
    @Email(message = "Not a valid Email address!")
    private String email;

    @NotNull(message = "SSN is required!")
    @Size(min = 9, max = 9, message = "The SSN should be exactly 9 digits!")
    @Pattern(regexp = "^[0-9]{9}", message = "SSN should contain only digits!")
    private String ssn;



    public UserModel(Long userID, String firstName, String lastName, String email, String ssn) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.ssn = ssn;
    }

}
