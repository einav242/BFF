package com.example.bff.controller;

import android.text.TextUtils;

import com.example.bff.entities.User;
import com.example.bff.model.MainActivityModel;
import com.example.bff.view.MainActivityView;


import javax.annotation.Nullable;

public class MainActivityController {

    MainActivityView view;
    MainActivityModel model;
    String permit;
    public MainActivityController(MainActivityView view) {
        this.view = view;
        model = new MainActivityModel(this);
        permit = "empty";
    }
    public void check_empty(String username, String  passwordE, String permit) {
        this.permit = permit;
        if (permit == "empty") {
            view.noPermit();
        } else if (TextUtils.isEmpty(username)) {
            view.noUserName();
        } else if (TextUtils.isEmpty(passwordE)) {
            view.noPassword();
        }
        else {
            model.login(username, passwordE, permit);
        }
    }
    public void  toast_controller(String msg){
        view.toast_view(msg);
    }

    public void passActivity_controller(@Nullable User user){
        if (this.permit == "animal") {
            view.toast_view("User logged in successfully");
            view.paasAnimalActivity(user);
        } else if (permit == "business") {
            view.toast_view("User logged in successfully");
            System.out.println("here!!!!");
            view.passBusinessActivity();
        }

    }

}
