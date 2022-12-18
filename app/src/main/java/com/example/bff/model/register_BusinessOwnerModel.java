package com.example.bff.model;



import androidx.annotation.NonNull;

import com.example.bff.controller.register_BusinessOwnerController;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class register_BusinessOwnerModel {
    register_BusinessOwnerController controller;
    private DatabaseReference mRootRef;
    private FirebaseAuth mAuth;

    public register_BusinessOwnerModel(register_BusinessOwnerController controller) {
        this.controller = controller;
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }

    public void registerUserModel(final String username, final String name, final String email, String password ,String id ,String phone,
                              String city , String street, String houseNumber, String type, String time) {

        controller.setPdController("Please Wait!");

        mAuth.createUserWithEmailAndPassword(email , password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                HashMap<String , Object> map = new HashMap<>();
                map.put("name" , name);
                map.put("email", email);
                map.put("username" , username);
                map.put("id" , mAuth.getCurrentUser().getUid());
                map.put("businessID", id);
                map.put("phone", phone);
                map.put("city", city);
                map.put("street", street);
                map.put("houseNumber",houseNumber);
                map.put("type",type);
                map.put("time",time);
                map.put("flag","business");

                mRootRef.child("Business").child(mAuth.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            controller.pdDismissController();
                            controller.setToastController("Update the profile for better expereince");
                            controller.passPageController();

                        }
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                controller.pdDismissController();
                controller.setToastController(e.getMessage());
            }
        });

    }
}
