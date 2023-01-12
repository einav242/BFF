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
    int count = 0;
    String id;

    public postSalesModel(postSalesController controller, String id) {
        this.id = id;
        this.controller = controller;
    }


    public void sendSalesModel(String description, String choice) {
        count();
        FirebaseDatabase.getInstance().getReference().child("Business").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Business user = dataSnapshot.getValue(Business.class);
                sale s = new sale(description, user.getUsername(), choice, user.getPhone(),String.valueOf(count),"ok",id,user.getImage());
                FirebaseDatabase.getInstance().getReference().child("Sales").child(id).child(String.valueOf(count)).setValue(s).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
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


    public void count(){
        FirebaseDatabase.getInstance().getReference().child("Sales").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    count = 0;
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        count++;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
}
