package com.example.bff.entities;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String email;
    private String date;
    private String time;
    private String status;

    public Client() {
    }

    public Client(String email, String date, String time, String status) {
        this.email=email;
        this.date=date;
        this.time=time;
        this.status=status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}