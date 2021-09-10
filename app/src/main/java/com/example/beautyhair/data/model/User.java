package com.example.beautyhair.data.model;

public class User {
    public enum UserType
    {
        ADMIN,
        CUSTOMER,
        SHOPKEEPER
    }

    private String Name;
    private String Password;
    private UserType Type;

    public User()
    {
    }

    public User(String name, String password, UserType type)
    {
        Name = name;
        Password = password;
        Type = type;
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

    public UserType getType() {
        return Type;
    }

    public void setType(UserType type) {
        Type = type;
    }
}
