package com.example.bff.controller;

import android.text.TextUtils;
import android.widget.Toast;

import com.example.bff.model.register_BusinessOwnerModel;
import com.example.bff.view.register_BusinessOwnerView;

public class register_BusinessOwnerController {
    register_BusinessOwnerModel model;
    register_BusinessOwnerView view;

    public register_BusinessOwnerController(register_BusinessOwnerView view) {
        this.view = view;
        model = new register_BusinessOwnerModel(this);
    }

    public void registerUserController(String txtUsername, String txtName, String txtEmail, String txtPassword, String txtId,
                                       String txtPhone, String txtCity, String txtStreet, String txtHouseNumber, String txtType, String txtTime) {

        if (TextUtils.isEmpty(txtUsername) || TextUtils.isEmpty(txtName)
                || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword) || TextUtils.isEmpty(txtId)
                || TextUtils.isEmpty(txtPhone) || TextUtils.isEmpty(txtCity) || TextUtils.isEmpty(txtStreet)
                || TextUtils.isEmpty(txtHouseNumber) || TextUtils.isEmpty(txtType) || TextUtils.isEmpty(txtTime)){
            view.setToastView("Empty credentials!");
        } else if (txtPassword.length() < 6){
            view.setToastView("Password too short!");
        } else {
            model.registerUserModel(txtUsername , txtName , txtEmail , txtPassword, txtId, txtPhone, txtCity, txtStreet, txtHouseNumber, txtType, txtTime);
        }
    }
    public void setToastController(String msg){
        view.setToastView(msg);
    }
    public void setPdController(String msg){
        view.setPdView(msg);
    }
    public void pdDismissController(){
        view.pdDismissView();
    }
    public void passPageController(){
        view.passPage();
    }

}
