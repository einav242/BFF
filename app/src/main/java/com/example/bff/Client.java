package com.example.bff;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String phone;
    private String email;

    public Client() {
    }

    public Client(String phone, String email) {
        this.phone=phone;
        this.email=email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}