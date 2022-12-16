package com.example.bff.controller;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.example.bff.Business;
import com.example.bff.User;
import com.example.bff.model.EditUserProfileModel;
import com.example.bff.model.animalActivityModel;
import com.example.bff.view.EditUserProfileView;



public class EditUserProfileController {

    EditUserProfileModel model;
    EditUserProfileView view;


    public EditUserProfileController(User user, Context context) {
        model = new EditUserProfileModel(user,context);
    }


    public void update(String edfullName, String edAnimalName, String edPhone) {
        model.update(edfullName,edAnimalName,edPhone);

    }

    public void EditProfileimage_controller(ImageView profilePic) {
        model.EditProfileimage_controller(profilePic);
    }
    public void uploadPicture_controller(Uri imageUri)
    {
        model.uploadPicture_model(imageUri);
    }


//    public void data(String email, String fullName, String animalName, String phone) {
//        model.setDataModel(email,fullName,animalName,phone);
//    }
//
//    public void setDataController(User user) {
//        view.setDataView(user);
//    }
}
