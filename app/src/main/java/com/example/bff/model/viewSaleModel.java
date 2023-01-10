package com.example.bff.model;

import androidx.annotation.NonNull;

import com.example.bff.controller.viewSaleController;
import com.example.bff.entities.User;
import com.example.bff.entities.sale;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class viewSaleModel {
    viewSaleController controller;
    FirebaseAuth mAuth;
    DatabaseReference mroot;

    public viewSaleModel(viewSaleController controller) {
        this.controller = controller;
        mAuth = FirebaseAuth.getInstance();
        mroot = FirebaseDatabase.getInstance().getReference("Sales");
    }

    public void findType(){
        FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                controller.setTypeController(user.getType());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });    }

    public void sendModelAdapter(ArrayList<sale> lst, String type) {
        mroot.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    sale s = dataSnapshot.getValue(sale.class);
                    if (s.getStatus().equals("ok") && (s.getAnimal().toLowerCase(Locale.ROOT).equals(type.toLowerCase(Locale.ROOT)) || s.getAnimal().equals("dog and cat")))
                    {
                        lst.add(s);
                    }
                }
                controller.setListController(lst);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void removeSaleModel(sale s) {
        s.setStatus("delete");
        FirebaseDatabase.getInstance().getReference("Sales").child(mAuth.getCurrentUser().getUid()).child(s.getKey()).setValue(s).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    controller.setScreenController();
                }
                else {}

            }
        });
    }
}
