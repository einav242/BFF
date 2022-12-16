package com.example.bff.controller;

import android.text.TextUtils;
import android.util.Patterns;

import com.example.bff.model.forget_passwordModel;
import com.example.bff.view.forget_passwordView;

public class forget_passwordController {
    forget_passwordModel model;
    forget_passwordView view;

    public forget_passwordController(forget_passwordView view) {
        this.view = view;
        this.model = new forget_passwordModel(this);
    }
    public void checkempty(String email){
        if(TextUtils.isEmpty(email))
        {
            view.setToast("Please enter your email");
            view.errorEmail("Email is required");
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            view.setToast("Please enter vaild email");
            view.errorEmail("Valid email is required");
        }else{
           model.resetPassword(email);
        }
    }
    public void setToastController(String msg){
        view.setToast(msg);
    }
    public void passActivityController(){
        view.passActivityView();
    }
}
