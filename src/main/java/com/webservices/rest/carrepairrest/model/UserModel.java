package com.webservices.rest.carrepairrest.model;

import javax.validation.constraints.*;

public class UserModel {

    public interface UserInsert {
    }

    public interface UserUpdate {
    }

    @Null(groups = {UserInsert.class}, message = "UserID should be null when you create a new user!")
    @NotNull(groups = {UserUpdate.class}, message = "UserID should not be null when updating a user!")
    private Long userID;

    @NotNull(groups = {UserInsert.class, UserUpdate.class}, message = "First Name is required!")
    @Size(groups = {UserInsert.class, UserUpdate.class}, min = 1, max = 128, message = "Maximum length is 128 characters!")
    @Pattern(groups = {UserInsert.class, UserUpdate.class}, regexp = "^[a-zA-Z]{1,128}", message = "First Name should contain only letters!")
    private String firstName;

    @NotNull(groups = {UserInsert.class, UserUpdate.class}, message = "Last Name is required!")
    @Size(groups = {UserInsert.class, UserUpdate.class}, min = 1, max = 128, message = "Maximum length is 128 characters!")
    @Pattern(groups = {UserInsert.class, UserUpdate.class}, regexp = "^[a-zA-Z]{1,128}", message = "Last Name should contain only letters!")
    private String lastName;

    @NotNull(groups = {UserInsert.class, UserUpdate.class}, message = "Email is required!")
    @Size(groups = {UserInsert.class, UserUpdate.class}, min = 1, max = 128, message = "Maximum length is 128 characters!")
    @Email(groups = {UserInsert.class, UserUpdate.class}, message = "Not a valid Email address!")
    private String email;

    @NotNull(groups = {UserInsert.class, UserUpdate.class}, message = "SSN is required!")
    @Size(groups = {UserInsert.class, UserUpdate.class}, min = 9, max = 9, message = "The SSN should be exactly 9 digits!")
    @Pattern(groups = {UserInsert.class, UserUpdate.class}, regexp = "^[0-9]{9}", message = "SSN should contain only digits!")
    private String ssn;

    public UserModel() {
    }

    public UserModel(Long userID, String firstName, String lastName, String email, String ssn) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.ssn = ssn;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}
