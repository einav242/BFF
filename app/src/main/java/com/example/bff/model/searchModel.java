package com.example.bff.model;

import androidx.annotation.NonNull;

import com.example.bff.entities.Business;
import com.example.bff.entities.User;
import com.example.bff.view.BusinessAdapter;
import com.example.bff.controller.searchController;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class searchModel {
    searchController controller;
    DatabaseReference databaseReference;
    ArrayList<Business> lst;

    public searchModel(searchController controller) {
        this.controller = controller;
        databaseReference = FirebaseDatabase.getInstance().getReference("Business");
        lst = new ArrayList<>();

    }
    public void addValueModel(BusinessAdapter myadapt)
    {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Business business = dataSnapshot.getValue(Business.class);
                    lst.add(business);
                }
                myadapt.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        controller.setlstController(lst);
    }
}
