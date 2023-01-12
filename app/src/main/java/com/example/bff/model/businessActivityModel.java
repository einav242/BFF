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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class businessActivityModel {
    businessActivityController controller;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private FirebaseAuth fAuth;
    DatabaseReference reff;
    String id;

    public businessActivityModel(businessActivityController controller, String id) {
        this.id = id;
        this.controller = controller;
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        fAuth =  FirebaseAuth.getInstance();
        reff =  FirebaseDatabase.getInstance().getReference().child("Business").child(id).child("news");
    }

    public void getUserNameModel(){
        FirebaseDatabase.getInstance().getReference().child("Business").child(id).addValueEventListener(new ValueEventListener() {
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
        FirebaseDatabase.getInstance().getReference().child("Business").child(id).addValueEventListener(new ValueEventListener() {
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
       this.reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot dataSnapshot: snapshot.getChildren())
                    {
                        Notification notification = dataSnapshot.getValue(Notification.class);
                        if(notification.getStatus().equals("new")) {
                            lst.add(notification);
                        }

                    }
                    controller.SetNews(lst);
                    reff.removeEventListener(this);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void  SetNewsToOld(ArrayList<Notification> lst){
        for(Notification n: lst){
            n.setStatus("old");
            FirebaseDatabase.getInstance().getReference().child("Business").child(id).child("news").child(n.getId()).setValue(n).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                    }
                }
            });
        }
    }
}

