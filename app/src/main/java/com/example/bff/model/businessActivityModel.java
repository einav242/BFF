package com.example.bff.model;

import android.net.Uri;


import androidx.annotation.NonNull;

import com.example.bff.controller.businessActivityController;
import com.example.bff.entities.Business;
import com.example.bff.entities.Notfiaction;
import com.example.bff.entities.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

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
    public void GetNews(ArrayList<Notfiaction> lst) {
        FirebaseDatabase.getInstance().getReference().child("Business").child(mAuth.getUid()).child("news").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot dataSnapshot: snapshot.getChildren())
                    {
                        Notfiaction notfiaction = dataSnapshot.getValue(Notfiaction.class);
                        if(notfiaction.getStatus().equals("new")) {
                            lst.add(notfiaction);
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

    public void SetNewsToOld(ArrayList<Notfiaction> lst) {
        for (Notfiaction s: lst){
            s.SetNotNew();
            FirebaseDatabase.getInstance().getReference().child("Business").child(mAuth.getUid()).child("news").setValue(s).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){

                    }
                }
            });
        }
    }
}
