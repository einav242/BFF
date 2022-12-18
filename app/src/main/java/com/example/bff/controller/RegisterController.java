package com.example.bff.controller;


import android.app.ProgressDialog;

import com.example.bff.entities.User;
import com.example.bff.model.RegisterModel;
import com.example.bff.view.RegisterView;

public class RegisterController {

    RegisterModel model;
    RegisterView view;

    public RegisterController(RegisterView view){
        this.view = view;
        model = new RegisterModel(this);
    }

    public void registerUser(String txtUsername, String txtName, String txtEmail, String txtPassword, String txtPhone, ProgressDialog pd) {
        model.registerUser(txtUsername,txtName,txtEmail,txtPassword,txtPhone,pd);
    }
    public void d() {
        view.p();
    }
    public void toast_controller(String s) {
        view.toast_view(s);
    }
}
