package com.andriginting.internshiptest.model;

/**
 * Created by Andri Ginting on 2/2/2018.
 */

public class LoginModel {
    public String username;
    public String password;

    public LoginModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
