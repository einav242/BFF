package com.example.bff.model;

import androidx.annotation.NonNull;

import com.example.bff.controller.viewQueueController;
import com.example.bff.entities.Business;
import com.example.bff.entities.User;
import com.example.bff.entities.queue;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class viewQueueModel {

    viewQueueController controller;
    DatabaseReference databaseReference;
    private FirebaseUser mAuth;
    String email;


    public viewQueueModel(viewQueueController controller) {
        this.controller = controller;
        databaseReference = FirebaseDatabase.getInstance().getReference("queue");
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
    }
    public void getListModel(HashMap<String,String> names, ArrayList<queue> lst){
        FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                email = user.getEmail();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    String name= names.get(dataSnapshot.getKey().toString());
                    for(DataSnapshot dataSnapshot2 : dataSnapshot.getChildren())
                    {
                        queue q = dataSnapshot2.getValue(queue.class);
                        if(q.getEmail().equals(email))
                        {
                            lst.add(q);
                        }
                    }

                }
                controller.notifyController(lst);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void getBusinessNameModel() {
        HashMap<String, String> names = new HashMap<>();
        FirebaseDatabase.getInstance().getReference("Business").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Business business = dataSnapshot.getValue(Business.class);
                    names.put(business.getId(), business.getUsername());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        controller.setBusinessNameController(names);
    }
}
