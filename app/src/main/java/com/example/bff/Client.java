package com.example.bff;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String phone;
    private String date;
    private String email;

    public Client() {
    }

    public Client(String phone, String date,String email) {
        this.phone=phone;
        this.date=date;
        this.email=email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
