package com.example.bff.controller;

import com.example.bff.Client;
import com.example.bff.model.make_appointmentModel;
import com.example.bff.view.make_appointmentView;

import java.util.List;

public class make_appointmentController {
    make_appointmentModel model;
    make_appointmentView view;

    public make_appointmentController(make_appointmentView view) {
        this.view = view;
        model = new make_appointmentModel(this);
    }
    public void clientList(String businessID){
        model.clientList(businessID);
    }
    public void setListController(List<Client> clientList){
        view.setListView(clientList);
    }
    public void getEmailController(){
        model.getEmailModel();
    }
    public void setEmailController(String email){
        view.setEmailView(email);
    }
    public void sendModel(){

    }
}
