package com.andriginting.internshiptest.model;

/**
 * Created by Andri Ginting on 2/2/2018.
 */

public class RegisterModel {
    public String firstName;
    public String lastName;
    public String username;
    public String password;
    public String bdate;
    public String gender;
    public String phone;

    public RegisterModel(String firstName, String lastName, String username, String password, String bdate, String gender, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.bdate = bdate;
        this.gender = gender;
        this.phone = phone;
    }
}
