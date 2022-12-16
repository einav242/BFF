package com.example.bff.model;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.example.bff.controller.forget_passwordController;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forget_passwordModel {
    forget_passwordController controller;
    private FirebaseAuth authProfile;

    public forget_passwordModel(forget_passwordController c) {
        this.controller = c;
    }
    public void resetPassword(String email) {
        authProfile = FirebaseAuth.getInstance();
        authProfile.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    controller.setToastController("please cheke youre inbox for password resert link");
                    controller.passActivityController();
                    controller.setToastController("Email send to Register Email Address");
                }else{
                    controller.setToastController("Something went worng");
                }
            }
        });
    }
}
