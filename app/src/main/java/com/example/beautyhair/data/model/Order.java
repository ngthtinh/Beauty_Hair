package com.example.beautyhair.data.model;

import java.util.Date;

public class Order {
    public enum StatusType
    {   PROCESSING,
        DONE,
        RATED
    }

    private String Id;
    private String Customer;
    private String Shopkeeper;
    private StatusType Status;

    public Order() {}

    public Order(String customer, String shopkeeper)
    {
        Date date = new Date();
        Id = customer + " " + shopkeeper + " " + date.toString();
        Customer = customer;
        Shopkeeper = shopkeeper;
        Status = StatusType.PROCESSING;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCustomer() {
        return Customer;
    }

    public void setCustomer(String customer) {
        Customer = customer;
    }

    public String getShopkeeper() {
        return Shopkeeper;
    }

    public void setShopkeeper(String shopkeeper) {
        Shopkeeper = shopkeeper;
    }

    public StatusType getStatus() {
        return Status;
    }

    public void setStatus(StatusType status) {
        Status = status;
    }
}
