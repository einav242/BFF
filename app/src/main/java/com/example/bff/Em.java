package com.example.bff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Em {

    private String businessEmail;
    private HashMap<String,Client> clients;

    public Em() {
        this.clients=new HashMap<>();
    }

    public Em(String email,HashMap<String,Client> clients) {
        this.businessEmail = email;
        this.clients=clients;
    }

    public HashMap<String, Client> getClients() {
        return clients;
    }

    public void setClients(HashMap<String, Client> clients) {
        this.clients = clients;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

}