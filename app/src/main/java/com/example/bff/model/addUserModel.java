package com.example.bff.model;

import androidx.annotation.NonNull;

import com.example.bff.controller.addUserController;
import com.example.bff.entities.Notification;
import com.example.bff.entities.User;
import com.example.bff.entities.queue;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    String id;

    public addUserModel(addUserController controller, String id) {
        this.id = id;
        this.controller = controller;
        mAuth = FirebaseAuth.getInstance();
        mroot = FirebaseDatabase.getInstance().getReference("queue");
    }

    public void getListModel(ArrayList<queue> lst){
        mroot.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    queue q = dataSnapshot.getValue(queue.class);
                    if(q.getStatus().equals("waiting"))
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
