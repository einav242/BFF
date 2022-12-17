package com.example.bff.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bff.Business;
import com.example.bff.R;
import com.example.bff.controller.seeMoreController;
import com.example.bff.make_appointment;
import com.example.bff.seeMore;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class seeMoreView extends AppCompatActivity {
    String email;
    String id;
    TextView tv_name, tv_email, tv_phone, tv_city, tv_street, tv_streetHouse, tv_ActivityTime;
    Button send;
    seeMoreController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_more);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            email = extras.getString("key");
        }
        controller = new seeMoreController(this);
        send = findViewById(R.id.button7);
        tv_name = findViewById(R.id.textView25);
        tv_email = findViewById(R.id.textView35);
        tv_phone = findViewById(R.id.textView33);
        tv_city = findViewById(R.id.textView27);
        tv_street = findViewById(R.id.textView29);
        tv_streetHouse = findViewById(R.id.textView37);
        tv_ActivityTime = findViewById(R.id.textView39);
        controller.data(email);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(seeMoreView.this, make_appointmentView.class);
                intent.putExtra("key",id);
                intent.putExtra("name",tv_name.getText().toString());
                startActivity(intent);
            }
        });
    }
    public void setDataView(Business business){
        tv_name.setText(business.getUsername());
        tv_email.setText(business.getEmail());
        tv_phone.setText(business.getPhone());
        tv_city.setText(business.getCity());
        tv_street.setText(business.getStreet());
        tv_streetHouse.setText(business.getHouseNumber());
        tv_ActivityTime.setText(business.getTime());
        id = business.getId();
    }

}