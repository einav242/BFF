package com.example.bff.controller;

import com.example.bff.model.postSalesModel;
import com.example.bff.view.postSalesView;

public class postSalesController {
    postSalesModel model;
    postSalesView view;

    public postSalesController(postSalesView view, String id) {
        this.view = view;
        model = new postSalesModel(this, id);
    }

    public void sendSalesController(String description, String choice) {
        model.sendSalesModel(description,choice);
    }

    public void setToastController(String msg){
        view.setToastView(msg);
    }
    public void setPdController(){
        view.setPd();
    }
}
