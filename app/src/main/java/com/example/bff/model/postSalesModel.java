package com.example.bff.model;

import androidx.annotation.NonNull;

import com.example.bff.controller.postSalesController;
import com.example.bff.entities.Business;
import com.example.bff.entities.sale;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class postSalesModel {
    postSalesController controller;
    private FirebaseAuth mAuth;


    public postSalesModel(postSalesController controller) {
        this.controller = controller;
        mAuth = FirebaseAuth.getInstance();
    }

    public void sendSalesModel(String description, String choice) {
        FirebaseDatabase.getInstance().getReference().child("Business").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Business user = dataSnapshot.getValue(Business.class);
                sale s = new sale(description,user.getUsername(),choice,user.getPhone());
                FirebaseDatabase.getInstance().getReference().child("Sales").child(mAuth.getUid()).setValue(s).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            controller.setPdController();
                            controller.setToastController("send message");
                        }
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
