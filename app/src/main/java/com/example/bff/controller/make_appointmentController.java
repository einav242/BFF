package com.example.bff.controller;
import com.example.bff.model.make_appointmentModel;
import com.example.bff.view.make_appointmentView;

public class make_appointmentController {
    make_appointmentModel model;
    make_appointmentView view;

    public make_appointmentController(make_appointmentView view) {
        this.view = view;
        model = new make_appointmentModel(this);
    }
    public void getEmailController(){
        model.getEmailModel();
    }
    public void setEmailController(String email){
        view.setEmailView(email);
    }
    public void sendController(String email, String txt_date,String txt_time,String id, String businessID){
            model.sendModel(email,txt_date,txt_time,id,businessID);
    }
    public void setToastController(String msg){
        view.setToastView(msg);
    }
    public void setPdController(){
        view.setPd();
    }
}
