package com.example.bff.controller;

import android.content.Context;

import com.example.bff.entities.User;
import com.example.bff.model.gotLostModel;
import com.example.bff.view.gotLostView;

public class gotLostController {
    gotLostModel model;
    gotLostView view;

    public gotLostController(gotLostView view) {
        this.view = view;
        model = new gotLostModel(this);
    }

    public void found() {
        model.found();
    }

    public void toast_controller(String s) {
        view.toast_view(s);
    }

    public void addLost() {
        model.addLost();
    }

    public void d(String ket, User user) {
        view.p(ket,user);
    }
}
