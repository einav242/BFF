package com.example.bff.controller;

import android.text.TextUtils;

import com.example.bff.User;
import com.example.bff.model.MainActivity_model;
import com.example.bff.view.MainActivity_view;


import javax.annotation.Nullable;

public class MainActivity_controller {

    MainActivity_view view;
    MainActivity_model model;
    String permit;
    public MainActivity_controller(MainActivity_view view) {
        this.view = view;
        model = new MainActivity_model(this);
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
                view.passBusinessActivity();
            }

    }

}
