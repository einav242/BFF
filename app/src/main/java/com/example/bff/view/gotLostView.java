package com.example.bff.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bff.R;
import com.example.bff.controller.animalActivityController;
import com.example.bff.controller.gotLostController;
import com.example.bff.entities.User;
import com.example.bff.get_lost;
import com.example.bff.list_gotLost;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class gotLostView extends AppCompatActivity {
    private Button addLost;
    private Button lostView;
    private Button found;
    ProgressDialog pd;
    private User user;
    private gotLostController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_lost);

        controller = new gotLostController(this);

        pd = new ProgressDialog(this);
        addLost = findViewById(R.id.get_lost_IgotLost);
        lostView = findViewById(R.id.get_lost_viewlost);
        found = findViewById(R.id.get_lost_foundMe);


        // if they found me
        found.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.found();
            }
        });

        //view all dog and cat that got lost
        lostView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(gotLostView.this, list_gotLost.class));
            }
        });

        //add lost pet
        addLost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.addLost();
            }
        });
    }

    public void  toast_view(String msg){
        Toast.makeText(gotLostView.this,msg, Toast.LENGTH_SHORT).show();
    }

    public void p(String key, User user) {
        Intent intent = new Intent(gotLostView.this, animalActivityView.class);
        intent.putExtra(key, user);
        startActivity(intent);
        finish();
    }
}
