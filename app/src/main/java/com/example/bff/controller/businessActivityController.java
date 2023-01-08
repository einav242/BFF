package com.example.bff.controller;

import android.net.Uri;
import android.widget.ImageView;

import com.example.bff.entities.Business;
import com.example.bff.entities.Notfiaction;
import com.example.bff.entities.User;
import com.example.bff.model.businessActivityModel;
import com.example.bff.view.businessActivityView;

import java.util.ArrayList;

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

    public void logout() {
        model.logout();
    }

    public void GetNotfications(ArrayList<Notfiaction> lst) {
        model.GetNews(lst);
    }

    public void SetNews(ArrayList<Notfiaction> lst) {
        view.SetNews(lst);
    }

    public void SetNewsToOld(ArrayList<Notfiaction> lst) {
        model.SetNewsToOld(lst);
    }

}
