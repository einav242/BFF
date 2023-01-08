package com.example.bff.entities;

public class Notfiaction {
    private String message;
    private String status;


    public Notfiaction(String s) {
        message=s;
        status="new";
    }

    public Notfiaction() {

    }

    public void SetNotNew(){
        this.status="old";
    }

    public String getStatus() {
        return this.status;
    }

    public String GetString() {
        return this.message;
    }
}
