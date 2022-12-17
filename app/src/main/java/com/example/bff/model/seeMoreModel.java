package com.example.bff.model;

import androidx.annotation.NonNull;

import com.example.bff.entities.Business;
import com.example.bff.controller.seeMoreController;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class seeMoreModel {
    seeMoreController controller;
    DatabaseReference databaseReference;

    public seeMoreModel(seeMoreController controller) {
        this.controller = controller;
        databaseReference = FirebaseDatabase.getInstance().getReference("Business");

    }
    public void setDataModel(String email){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Business business = dataSnapshot.getValue(Business.class);
                    if(business.getEmail().equals(email))
                    {
                        controller.setDataController(business);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
