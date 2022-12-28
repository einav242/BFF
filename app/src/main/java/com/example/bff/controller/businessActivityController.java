package com.example.bff.controller;

import android.net.Uri;
import android.widget.ImageView;

import com.example.bff.entities.Business;
import com.example.bff.entities.User;
import com.example.bff.model.businessActivityModel;
import com.example.bff.view.businessActivityView;

public class businessActivityController {
    businessActivityView view;
    businessActivityModel model;

    public businessActivityController(businessActivityView view) {
        this.view = view;
        model = new businessActivityModel(this);
    }

    public void getUserNameController(){
        model.getUserNameModel();
    }
    public void setUserName(String name){
        view.setUserName(name);
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

    public void picassoController(Uri uri){
        view.picassoView(uri);
    }

    public void getImageProfile() {
        model.getImageProfileModel();
    }

    public void setImage(Business user) {
        view.setImage(user);
    }
}
