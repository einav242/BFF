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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class list_gotLostModel {
    DatabaseReference databaseReference;
    list_gotLostController controller;
    ArrayList<User> lst;

    private FirebaseAuth fAuth;
    private StorageReference storageReference;
    private FirebaseStorage storage;

    public list_gotLostModel(list_gotLostController controller) {
        this.controller = controller;
        databaseReference = FirebaseDatabase.getInstance().getReference("Got Lost");
        this.storage = FirebaseStorage.getInstance();
        this.storageReference = storage.getReference();
        this.fAuth =  FirebaseAuth.getInstance();
    }

    public void allListModel( ArrayList<User> lst){
        databaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        User user = dataSnapshot.getValue(User.class);
                        lst.add(user);
                }
                controller.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
