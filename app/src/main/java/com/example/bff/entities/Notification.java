package com.example.bff.entities;

public class Notification {
    private String message;
    private String status;
    private String id;


    public Notification(String s, String id) {
        message=s;
        status="new";
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
