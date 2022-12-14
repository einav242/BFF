package com.example.bff.controller;



import android.text.TextUtils;
import com.example.bff.model.RegisterModel;
import com.example.bff.view.RegisterView;

public class RegisterController {

    RegisterModel model;
    RegisterView view;

    public RegisterController(RegisterView view)
    {
        this.view = view;
        model = new RegisterModel(this);
    }

    public void registerUser(String txtUsername, String txtName, String txtEmail, String txtPassword, String txtPhone , String txtBreed , String txtColor , String txtType) {
        if (TextUtils.isEmpty(txtUsername) || TextUtils.isEmpty(txtName)
                || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword) || TextUtils.isEmpty(txtPhone) || TextUtils.isEmpty(txtColor) || TextUtils.isEmpty(txtBreed) ||
                TextUtils.isEmpty(txtType)){
            view.toast_view("Empty credentials!");
        } else if (txtPassword.length() < 6){
            view.toast_view("Password too short!");
        } else {
            model.registerUser(txtUsername,txtName,txtEmail,txtPassword,txtPhone, txtBreed , txtColor , txtType);
//
        }
    }

    public void d() {
        view.p();
    }

    public void toast_controller(String s) {
        view.toast_view(s);
    }

    public void setPdController(String s) {
        view.setPdView(s);
    }

    public void pdDismissController() {
        view.pdDismissView();
    }
}
