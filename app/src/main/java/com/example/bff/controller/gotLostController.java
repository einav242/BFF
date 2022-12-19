package com.example.bff.controller;

import com.example.bff.model.gotLostModel;
import com.example.bff.view.getLostView;

public class gotLostController {
    gotLostModel model;
    getLostView view;

    public gotLostController(getLostView view) {
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

    public void d() {
        view.p();
    }
}
