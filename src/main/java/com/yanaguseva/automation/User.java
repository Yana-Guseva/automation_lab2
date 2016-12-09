package com.yanaguseva.automation;

public class User {
    private String email;
    private String passwod;

    public User(String email, String passwod) {
        this.email = email;
        this.passwod = passwod;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswod() {
        return passwod;
    }
}
