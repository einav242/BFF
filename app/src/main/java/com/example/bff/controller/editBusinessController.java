package com.example.bff.controller;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.example.bff.User;
import com.example.bff.model.EditUserProfileModel;
import com.example.bff.model.editBusinessModel;
import com.example.bff.view.editBusinessView;

public class editBusinessController {

    editBusinessModel model;
    public editBusinessController(User user, Context context) {
        model = new editBusinessModel(user,context);
    }

    public void update(String newName,String newBusinessName, String newId, String newPhone, String newCity, String newStreet, String newHouseNumber, String newType, String newTime) {
        model.update(newName,newBusinessName,newId,newPhone,newCity,newStreet,newHouseNumber,newType,newTime);
    }

    public void EditProfileimage_controller(ImageView profilePic) {
        model.EditProfileimage_controller(profilePic);
    }

    public void uploadPicture_controller(Uri imageUri) {
        model.uploadPicture_model(imageUri);
    }
}
