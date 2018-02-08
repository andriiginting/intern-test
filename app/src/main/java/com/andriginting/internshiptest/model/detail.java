package com.andriginting.internshiptest.model;

/**
 * Created by Andri Ginting on 2/2/2018.
 */

public class detail {
    public String userId;
    public String firstName;
    public String lastName;
    public String username;
    public String password;
    public String birthDate;
    public String gender;
    public String phoneNumber;

    public detail(String userId, String firstName, String lastName, String username, String password, String birthDate, String gender, String phoneNumber) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
