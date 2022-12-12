package com.example.bff;

import  com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Business {

    private String businessID;
    private String city;
    private String email;
    private String houseNumber;
    private String id;
    private String name;
    private String phone;
    private String street;
    private String type;
    private String username;
    private String time;


    public Business() {
    }

    public Business(String businessID, String city, String name, String email, String houseNumber,
                    String username, String phone, String id, String street, String type, String time) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.id = id;
        this.businessID = businessID;
        this.city = city;
        this.houseNumber = houseNumber;
        this.phone = phone;
        this.street = street;
        this.type = type;
        this.time = time;
    }

    public String getBusinessID() {
        return businessID;
    }
    public void setBusinessID(String businessID) {
        this.businessID = businessID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }


    public String getHouseNumber() {
        return houseNumber;
    }
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }


    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }


    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}