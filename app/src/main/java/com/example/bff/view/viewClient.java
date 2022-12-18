package com.example.bff.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.bff.R;
import com.example.bff.businessActivity;
import com.example.bff.controller.ViewClientController;
import com.example.bff.entities.Client;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class viewClient extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Client> lst;
//    DatabaseReference mroot;
    ClientAdapter myadapt;
//    FirebaseAuth mAuth;
    ViewClientController controller;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(viewClient.this , businessActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientlist);
        recyclerView = findViewById(R.id.Recycleview);
        controller=new ViewClientController(this);
//        mAuth = FirebaseAuth.getInstance();
//        mroot = FirebaseDatabase.getInstance().getReference("Em");
        lst=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myadapt = new ClientAdapter(lst,this,1);
        recyclerView.setAdapter(myadapt);
        controller.SendControllAdpter(myadapt,lst);
//        mroot.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot: snapshot.getChildren())
//                {
//                    Client client = dataSnapshot.getValue(Client.class);
//                    if(client.getStatus().equals("approve"))
//                        lst.add(client);
//                }
//                myadapt.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }
}