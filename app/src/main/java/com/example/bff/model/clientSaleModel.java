package com.example.bff.model;

import androidx.annotation.NonNull;

import com.example.bff.controller.clientSaleController;
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

    public clientSaleModel(clientSaleController controller) {
        this.controller = controller;
        mAuth = FirebaseAuth.getInstance();
        mroot = FirebaseDatabase.getInstance().getReference("Sales");

    }

    public void sendModelAdapter(ArrayList<sale> lst) {
        mroot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    for(DataSnapshot dataSnapshot2: dataSnapshot.getChildren())
                    {
                        sale s = dataSnapshot2.getValue(sale.class);
                        System.out.println("hiiiiii: "+s.getStatus());
                        if (s.getStatus().equals("ok"))
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
