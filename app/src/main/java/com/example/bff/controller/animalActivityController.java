package com.example.bff.controller;

import android.net.Uri;
import android.widget.ImageView;

import com.example.bff.entities.User;
import com.example.bff.model.animalActivityModel;
import com.example.bff.view.animalActivityView;


public class animalActivityController {

    animalActivityModel model;
    animalActivityView view;


    public animalActivityController(animalActivityView view) {
        model = new animalActivityModel(this);
        this.view = view;
    }

    public void imageListener_controller()
    {
        model.imageListener();
    }

    public void logOut_controller()
    {
        model.logOut_model();
    }

    public void uploadPicture_controller(Uri imageUri)
    {
        model.uploadPicture_model(imageUri);
    }

    public void getUserNameController(){
        model.getUserNameModel();
    }

    public void setNameController(String name){
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


    public void uploadPictureController(Uri imageUri) {
        model.uploadPicture_model(imageUri);
    }

    public void setImageController(Uri uri) {
        view.setImegeView(uri);
    }

    public void getImageProfile() {
        model.getImageProfileModel();
    }

    public void setImage(User user) {
        view.setImage(user);
    }
}