package com.example.bff.controller;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.example.bff.User;
import com.example.bff.model.animalActivityModel;
import com.example.bff.view.animalActivityView;
import com.squareup.picasso.Target;

import java.util.ConcurrentModificationException;
import java.util.HashMap;

public class animalActivity_controller {

    animalActivityModel model;


    public animalActivity_controller(User user, Context context) {
        model = new animalActivityModel(user,context);
    }

    public void imageListener_controller(ImageView profilePic)
    {
        model.imageListener(profilePic);
    }
    public HashMap<String, String>  getbusinessName_controller(){
        return model.getbusinessName_model();
    }
    public void logOut_controller()
    {
        model.logOut_model();
    }
    public void uploadPicture_controller(Uri imageUri)
    {
        model.uploadPicture_model(imageUri);
    }
}