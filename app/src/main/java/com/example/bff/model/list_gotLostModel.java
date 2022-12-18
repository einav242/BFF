package com.example.bff.model;

import androidx.annotation.NonNull;

import com.example.bff.controller.list_gotLostController;
import com.example.bff.entities.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class list_gotLostModel {
    ArrayList<User> list;
    DatabaseReference databaseReference;
    list_gotLostController controller;
    private FirebaseUser mAuth;

    public list_gotLostModel(list_gotLostController controller) {
        this.controller = controller;
        databaseReference = FirebaseDatabase.getInstance().getReference("Got Lost");
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
    }

    public void allListModel(ArrayList<User> list){
        databaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    list.add(user);
                }
                controller.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
