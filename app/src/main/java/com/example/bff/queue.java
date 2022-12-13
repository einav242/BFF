package com.example.bff;

public class queue {
    String businessName;
    String date;
    String time;
    String status;

    public queue(){}

    public queue(String businessName, String date, String time, String status) {
        this.businessName = businessName;
        this.date = date;
        this.time = time;
        this.status = status;
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
