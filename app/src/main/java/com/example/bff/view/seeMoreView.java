package com.example.bff.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bff.entities.Business;
import com.example.bff.R;
import com.example.bff.controller.seeMoreController;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class seeMoreView extends AppCompatActivity {
    String id;
    TextView tv_name, tv_email, tv_phone, tv_city, tv_street, tv_streetHouse, tv_ActivityTime;
    Button send;
    seeMoreController controller;
    CircleImageView profilePic;
    String image;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_more);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("key");
            userID = extras.getString("userID");
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
        profilePic = findViewById(R.id.imageView2);
        controller.data(id);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(seeMoreView.this, make_appointmentView.class);
                intent.putExtra("key",id);
                intent.putExtra("name",tv_name.getText().toString());
                intent.putExtra("image",image);
                intent.putExtra("userID",userID);
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
        image = business.getImage();
        Picasso.get().load(business.getImage()).placeholder(R.drawable.vetuser).into(profilePic);
    }

}