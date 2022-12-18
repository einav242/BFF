package com.example.bff.model;

import androidx.annotation.NonNull;

import com.example.bff.controller.addUserController;
import com.example.bff.entities.Client;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class addUserModel {
    addUserController controller;
    DatabaseReference mroot;
    FirebaseAuth mAuth;
    ArrayList<Client> lst;



    public addUserModel(addUserController controller) {
        this.controller = controller;
        mAuth = FirebaseAuth.getInstance();
        mroot = FirebaseDatabase.getInstance().getReference("Em");
    }
    public void getListModel(){
        mroot.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    Client client = dataSnapshot.getValue(Client.class);
                    if(client.getStatus().equals("waiting"))
                        lst.add(client);
                }
                controller.setList(lst);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
