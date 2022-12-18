package com.example.bff.controller;

import android.net.Uri;

import com.example.bff.model.EditUserProfileModel;
import com.example.bff.view.EditUserProfileView;



public class EditUserProfileController {

    EditUserProfileModel model;
    EditUserProfileView view;


    public EditUserProfileController(EditUserProfileView view) {
        this.view = view;
        model = new EditUserProfileModel(this);
    }

    public void setDataController(String name, String username, String email, String phone) {
        view.setDataView(name, username, email, phone);
    }

    public void getDataController(){
        model.getDataModel();
    }

    public void getImageController(){
        model.getImageModel();
    }

    public void setImageController(Uri uri) {
        view.setImegeView(uri);
    }

    public void updateController(String toString, String toString1, String toString2) {
        model.updateModel(toString,toString1,toString2);
    }

    public void uploadPictureController(Uri imageUri) {
        model.uploadPicture_model(imageUri);
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
}
