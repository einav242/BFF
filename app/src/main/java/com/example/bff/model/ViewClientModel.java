package com.example.bff.model;

import androidx.annotation.NonNull;

import com.example.bff.controller.ViewClientController;
import com.example.bff.entities.Client;
import com.example.bff.view.ClientAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewClientModel {
    ViewClientController controller;
    FirebaseAuth mAuth;
    DatabaseReference mroot;


    public ViewClientModel(ViewClientController viewClientController) {
        controller=viewClientController;
        mAuth = FirebaseAuth.getInstance();
        mroot = FirebaseDatabase.getInstance().getReference("Em");
    }

    public void SendModelAdpter(ArrayList<Client> lst) {
        mroot.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    Client client = dataSnapshot.getValue(Client.class);
                    if(client.getStatus().equals("approve"))
                        lst.add(client);
                }
                controller.setListController(lst);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
