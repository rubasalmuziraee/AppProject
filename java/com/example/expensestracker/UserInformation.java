package com.example.expensestracker;


public class UserInformation {

    private String name;
    private String email;
    private String pass;

    public UserInformation(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpass() {
        return pass;
    }

    public void setpass(String pass) {
        this.pass = pass;
    }
}