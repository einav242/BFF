package com.example.bff.model;

import androidx.annotation.NonNull;

import com.example.bff.controller.gotLostController;
import com.example.bff.entities.User;

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

public class gotLostModel {
    DatabaseReference mRootRef;
    gotLostController controller;
    String id;

    public gotLostModel(gotLostController controller, String id) {
        this.id = id;
        mRootRef = FirebaseDatabase.getInstance().getReference();
        this.controller = controller;
    }


    private void InserData(String phone,final String email,String username,String id , String breed , String color ,String type , String image) {
        User user = new User(phone,email,username,id,breed, color, type, image);
        mRootRef.child("Got Lost").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    controller.toast_controller("User Details Inserted");
                    controller.d();
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
        FirebaseDatabase.getInstance().getReference("Got Lost").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
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
                    controller.d();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addLost() {
        FirebaseDatabase.getInstance().getReference().child("Got Lost").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    controller.toast_controller("Your animal is already listed");
                }
                else
                {
                    FirebaseDatabase.getInstance().getReference().child("Users").child(id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            User user = dataSnapshot.getValue(User.class);
                            String email = user.getEmail();
                            String phone = user.getPhone();
                            String id = user.getId();
                            String username = user.getName();
                            String breed = user.getBreed();
                            String color = user.getColor();
                            String type = user.getType();
                            String image = user.getImage();
                            InserData(phone, email , username,id , breed ,color , type , image);
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
