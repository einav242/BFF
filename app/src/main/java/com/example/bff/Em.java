package com.example.bff;

import java.util.HashMap;

public class Em {

    private String businessName;
    private HashMap<String,Client> clients;

    public Em(){
        this.clients=new HashMap<>();
    }

//    public Em(String businessName) {
//        this.clients=new HashMap<>();
//        this.businessName = businessName;
//    }

    public Em(String email,HashMap<String,Client> clients) {
        this.businessName = email;
        this.clients=clients;
    }

    public HashMap<String, Client> getClients() {
        return clients;
    }

    public void setClients(HashMap<String, Client> clients) {
        this.clients = clients;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

}