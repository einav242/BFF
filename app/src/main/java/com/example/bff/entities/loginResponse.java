package com.example.bff.entities;

import java.io.Serializable;

import retrofit2.Call;

public class loginResponse implements Serializable {
    private String user_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


}
