package com.example.bff.model;


import androidx.annotation.NonNull;

import com.example.bff.controller.businessActivityController;
import com.example.bff.entities.Business;
import com.example.bff.entities.Notification;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class businessActivityModel {
    businessActivityController controller;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private FirebaseUser mAuth;
    private FirebaseAuth fAuth;


    public businessActivityModel(businessActivityController controller) {
        this.controller = controller;
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        fAuth =  FirebaseAuth.getInstance();
    }

    public void getUserNameModel(){
        FirebaseDatabase.getInstance().getReference().child("Business").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Business user = dataSnapshot.getValue(Business.class);
                controller.setUserName(user.getUsername());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void getImageProfileModel() {
        FirebaseDatabase.getInstance().getReference().child("Business").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Business user = dataSnapshot.getValue(Business.class);
                controller.setImage(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
    }
    public void GetNews(ArrayList<Notification> lst) {
        FirebaseDatabase.getInstance().getReference().child("Business").child(mAuth.getUid()).child("news").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot dataSnapshot: snapshot.getChildren())
                    {
                        Notification notification = dataSnapshot.getValue(Notification.class);
                        if(notification.getStatus().equals("new")) {
                            lst.add(notification);
                            SetNewsToOld(notification,dataSnapshot.getKey());
                        }

                    }
                    controller.SetNews(lst);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void SetNewsToOld(Notification notification,String id) {
        notification.SetNotNew();
        FirebaseDatabase.getInstance().getReference().child("Business").child(mAuth.getUid()).child("news").child(id).setValue(notification).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                }
            }
        });
    }
}

