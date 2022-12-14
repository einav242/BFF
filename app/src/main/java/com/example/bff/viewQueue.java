package com.example.bff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class viewQueue extends AppCompatActivity{

    RecyclerView recyclerView;
    ArrayList<queue> lst;
    DatabaseReference databaseReference;
    queueAdapter myadapt;
    HashMap<String,String> names;
    private FirebaseUser mAuth;
    String email;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(viewQueue.this, animalActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.queue_list);
        Intent intent = getIntent();
        names = (HashMap<String, String>) intent.getSerializableExtra("key");
        recyclerView = findViewById(R.id.Recycleview);
        databaseReference = FirebaseDatabase.getInstance().getReference("Em");
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        lst = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myadapt = new queueAdapter(lst,this);
        recyclerView.setAdapter(myadapt);
        FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                email = user.getEmail();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                   String name= names.get(dataSnapshot.getKey().toString());
                    System.out.println("name: "+name);
                    for(DataSnapshot dataSnapshot2 : dataSnapshot.getChildren())
                    {
                        Client client = dataSnapshot2.getValue(Client.class);
                        if(client.getEmail().equals(email))
                        {
                            queue q = new queue(name,client.getDate(),client.getTime(), client.getStatus());
                            lst.add(q);
                        }
                    }

                }
                myadapt.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}


