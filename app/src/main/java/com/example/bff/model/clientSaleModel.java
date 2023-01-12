package com.example.bff.model;

import androidx.annotation.NonNull;

import com.example.bff.controller.clientSaleController;
import com.example.bff.entities.User;
import com.example.bff.entities.sale;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class clientSaleModel {
    clientSaleController controller;
    FirebaseAuth mAuth;
    DatabaseReference mroot;
    String id;

    public clientSaleModel(clientSaleController controller, String id) {
        this.id = id;
        this.controller = controller;
        mAuth = FirebaseAuth.getInstance();
        mroot = FirebaseDatabase.getInstance().getReference("Sales");

    }
    public void findTypeModel(){
        FirebaseDatabase.getInstance().getReference().child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                controller.setTypeController(user.getType());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void sendModelAdapter(ArrayList<sale> lst, String type) {
        mroot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    for(DataSnapshot dataSnapshot2: dataSnapshot.getChildren())
                    {
                        sale s = dataSnapshot2.getValue(sale.class);
                        if (s.getStatus().equals("ok")&& (s.getAnimal().toLowerCase().equals(type.toLowerCase()) || s.getAnimal().equals("dog and cat")))
                        {
                            lst.add(s);
                        }
                    }
                }
                controller.setListController(lst);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
