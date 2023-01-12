package com.example.bff.controller;
import com.example.bff.entities.User;
import com.example.bff.model.make_appointmentModel;
import com.example.bff.view.make_appointmentView;

public class make_appointmentController {
    make_appointmentModel model;
    make_appointmentView view;

    public make_appointmentController(make_appointmentView view, String id) {
        this.view = view;
        model = new make_appointmentModel(this, id);
    }
    public void getEmailController(){
        model.getEmailModel();
    }
    public void setEmailController(User user){
        view.setEmailView(user);
    }
    public void sendController(String email, String txt_date,String txt_time,String id, String businessID, String businessName, String image, String userImage){
            model.sendModel(email,txt_date,txt_time,id,businessID, businessName, image, userImage);
    }
    public void setToastController(String msg){
        view.setToastView(msg);
    }
    public void setPdController(){
        view.setPd();
    }

}
