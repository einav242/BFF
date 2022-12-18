package com.example.bff.model;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.bff.controller.gotLostController;
import com.example.bff.entities.User;

import com.example.bff.view.animalActivityView;
import com.example.bff.view.gotLostView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
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

public class gotLostModel {
    FirebaseUser userF;
    DatabaseReference mRootRef;
    private FirebaseUser mAuth;
    FirebaseDatabase data;
    private Context context;
    private FirebaseAuth fAuth;
    private StorageReference storageReference;
    private FirebaseStorage storage;
    gotLostController controller;


    public gotLostModel(gotLostController controller) {
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        mRootRef = FirebaseDatabase.getInstance().getReference();
        fAuth =  FirebaseAuth.getInstance();
        this.controller = controller;
    }


    private void InserData(String phone,final String email,String username,String id) {
        User user = new User(phone,email,username,id);
        mRootRef.child("Got Lost").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    controller.toast_controller("User Details Inserted");
                    controller.d("ket",user);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                controller.toast_controller(e.getMessage());
            }
        });
    }

    public void found() {
        FirebaseDatabase.getInstance().getReference("Got Lost").child(mAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists() == false){
                    controller.toast_controller("Your animal not on the list");
                }
                else {
                    User user = snapshot.getValue(User.class);
                    String id = user.getId();
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Got Lost").child(id);
                    databaseReference.removeValue();
                    controller.toast_controller("User Details delete");
                    controller.d("ket",user);
//                    Intent intent = new Intent(gotLostView.this, animalActivityView.class);
//                    intent.putExtra("key", user);
//                    startActivity(intent);
//                    finish();
                }
//                finish();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        finish();
    }

    public void addLost() {
        FirebaseDatabase.getInstance().getReference().child("Got Lost").child(mAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    controller.toast_controller("Your animal is already listed");
                }
                else
                {
                    FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            User user = dataSnapshot.getValue(User.class);
                            String email = user.getEmail();
                            String phone = user.getPhone();
                            String id = user.getId();
                            String username = user.getName();
                            InserData(phone, email , username,id);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
