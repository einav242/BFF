package com.example.bff;

import com.example.bff.entities.Client;

import java.util.HashMap;

public class Em {

    private String businessName;
    private int id;
    private HashMap<String, Client> clients;

    public Em(){
        this.clients=new HashMap<>();
    }

//    public Em(String businessName) {
//        this.clients=new HashMap<>();
//        this.businessName = businessName;
//    }

    public Em(int id,String email,HashMap<String,Client> clients) {
        this.businessName = email;
        this.clients=clients;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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