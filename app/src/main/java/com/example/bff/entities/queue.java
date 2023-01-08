package com.example.bff.entities;

public class queue {
    String businessName;
    String date;
    String time;
    String status;
    String email;
    private String image;


    public queue(){}

    public queue(String businessName, String date, String time, String status,String email, String image) {
        this.businessName = businessName;
        this.date = date;
        this.time = time;
        this.status = status;
        this.image = image;
        this.email =email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
