package com.example.bff.model;

import androidx.annotation.NonNull;

import com.example.bff.controller.ViewClientController;
import com.example.bff.entities.queue;
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
    String id;

    public ViewClientModel(ViewClientController viewClientController, String id) {
        this.id = id;
        controller=viewClientController;
        mAuth = FirebaseAuth.getInstance();
        mroot = FirebaseDatabase.getInstance().getReference("queue");
    }

    public void SendModelAdpter(ArrayList<queue> lst) {
        mroot.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    queue q = dataSnapshot.getValue(queue.class);
                    if(q.getStatus().equals("approve"))
                        lst.add(q);
                }
                controller.setListController(lst);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
