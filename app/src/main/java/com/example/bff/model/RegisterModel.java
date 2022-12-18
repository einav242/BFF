package com.example.bff.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.bff.MainActivity;
import com.example.bff.Register;
import com.example.bff.controller.RegisterController;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterModel {

    RegisterController controller;
    private Context context;
    ProgressDialog pd;
    private FirebaseAuth mAuth;
    private DatabaseReference mRootRef;

    public RegisterModel(RegisterController controller){
        this.controller = controller;
        mAuth = FirebaseAuth.getInstance();
        mRootRef = FirebaseDatabase.getInstance().getReference();
    }

    public void registerUser(String txtUsername, String txtName,final String txtEmail, String txtPassword, String txtPhone) {
        controller.setPdController("Please Wait!");

        mAuth.createUserWithEmailAndPassword(txtEmail , txtPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                HashMap<String , Object> map = new HashMap<>();
                map.put("name" , txtName);
                map.put("email", txtEmail);
                map.put("username" , txtUsername);
                map.put("phone",txtPhone);
                map.put("flag","animal");
                map.put("id" , mAuth.getCurrentUser().getUid());

                mRootRef.child("Users").child(mAuth.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            controller.pdDismissController();
                            controller.toast_controller("Update the profile " + "for better expereince");
                            controller.d();
                        }
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                controller.pdDismissController();
                controller.toast_controller(e.getMessage());
            }
        });
    }
}
