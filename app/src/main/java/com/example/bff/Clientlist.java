package com.example.bff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Clientlist extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Client> lst;
    DatabaseReference mroot;
    ClientAdapter myadapt;
    FirebaseAuth mAuth;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Clientlist.this , addUser.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientlist);
        recyclerView = findViewById(R.id.Recycleview);
        mAuth = FirebaseAuth.getInstance();
        mroot = FirebaseDatabase.getInstance().getReference("Em");
        lst=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myadapt = new ClientAdapter(lst,this);
        recyclerView.setAdapter(myadapt);
        mroot.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    Client client = dataSnapshot.getValue(Client.class);
                    if(client.getStatus()=="waiting")
                        lst.add(client);
                }
                myadapt.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    Client client = dataSnapshot.getValue(Client.class);
                    lst.add(client);
                }
                myadapt.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mroot.child(mAuth.getCurrentUser().getUid()).addValueEventListener(valueEventListener);


    }
}