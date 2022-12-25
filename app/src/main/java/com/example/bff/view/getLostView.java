package com.example.bff.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bff.R;

import com.example.bff.controller.gotLostController;
import com.example.bff.entities.User;

public class getLostView extends AppCompatActivity {
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
                startActivity(new Intent(getLostView.this, list_gotLost_newView.class));
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
        Toast.makeText(getLostView.this,msg, Toast.LENGTH_SHORT).show();
    }

    public void p() {
        Intent intent = new Intent(getLostView.this, animalActivityView.class);
        startActivity(intent);
        finish();
    }
}
