package com.example.bff.entities;

public class Notification {
    private String message;
    private String status;


    public Notification(String s) {
        message=s;
        status="new";
    }

    public Notification() {

    }

    public void SetNotNew(){
        this.status="old";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public String GetString() {
        return this.message;
    }
}
