package com.example.bff.controller;

import android.net.Uri;

import com.example.bff.model.editBusinessModel;
import com.example.bff.view.editBusinessView;


public class editBusinessController {

    editBusinessModel model;
    editBusinessView view;


    public editBusinessController(editBusinessView view) {
        this.view = view;
        model = new editBusinessModel(this);
    }

    public void setDataController(String email ,String name, String businessName, String id ,String phone, String city,String street,
                                  String house_number, String type , String time) {
        view.setDataView(email,name, businessName, id, phone, city, street, house_number, type, time);
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

    public void updateController(String name, String businessName, String id ,String phone, String city,String street,
                        String house_number, String type , String time) {
        model.updateModel(name,businessName,id,phone,city, street, house_number, type, time);
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
    public void passPageController(){
        view.passPage();
    }

    public void updateImageController(Uri imageUri) {
        model.updateImageModel(imageUri);
    }
}
