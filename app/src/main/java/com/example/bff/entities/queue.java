package com.example.bff.entities;

public class queue {
    String businessName;
    String date;
    String time;
    String status;
    String email;
    private String business_image;
    private String user_image;


    public queue(){}

    public queue(String businessName, String date, String time, String status,String email, String business_image, String user_image) {
        this.businessName = businessName;
        this.date = date;
        this.time = time;
        this.status = status;
        this.business_image = business_image;
        this.user_image = user_image;
        this.email =email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBusiness_image() {
        return business_image;
    }

    public void setBusiness_image(String business_image) {
        this.business_image = business_image;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
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
