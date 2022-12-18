package com.example.bff.model;

import androidx.annotation.NonNull;

import com.example.bff.controller.list_gotLostController;
import com.example.bff.entities.Business;
import com.example.bff.entities.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class list_gotLostModel {
    ArrayList<User> lst;
    DatabaseReference databaseReference;
    list_gotLostController controller;
    private FirebaseUser mAuth;
    String email;

    public list_gotLostModel(list_gotLostController controller) {
        this.controller = controller;
        databaseReference = FirebaseDatabase.getInstance().getReference("Got Lost");
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
    }

    public void allListModel( ArrayList<User> lst){
//        FirebaseDatabase.getInstance().getReference().child("Got Lost").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                User user = dataSnapshot.getValue(User.class);
//                email = user.getEmail();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        databaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
//                    String name = names.get(dataSnapshot.getKey().toString());
//                    for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()){
                        User user = dataSnapshot.getValue(User.class);
                        lst.add(user);
                    //}

                }
//                controller.setListController(lst);
                controller.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

//    public void getUserNameModel() {
//        HashMap<String, String> names = new HashMap<>();
//        FirebaseDatabase.getInstance().getReference("Users").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    User user = dataSnapshot.getValue(User.class);
//                    names.put(user.getId(), user.getUsername());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        controller.setUserNameController(names);
//    }
}
