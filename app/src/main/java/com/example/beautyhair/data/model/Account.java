package com.example.beautyhair.data.model;

public class Account {
    private String Name;
    private String Password;

    public Account()
    {
    }

    public Account(String name, String password)
    {
        Name = name;
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
