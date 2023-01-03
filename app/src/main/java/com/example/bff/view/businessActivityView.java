package com.example.bff.view;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bff.R;
import com.example.bff.controller.businessActivityController;

import com.example.bff.entities.Business;
import com.google.firebase.auth.FirebaseAuth;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class businessActivityView extends AppCompatActivity {
    private ImageButton view;
    private ImageButton insert;
    private ImageButton edit;
    private ImageButton sales;
    private TextView title;
    private businessActivityController controller;
    private Button logOut;


    private ProgressDialog pd;
    CircleImageView profilePic;
    public Uri imageUri;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_window);
        view = findViewById(R.id.imageButton5);
        insert = findViewById(R.id.imageButton3);
        edit = findViewById(R.id.imageButton8);
        title = findViewById(R.id.txtMessage);
        profilePic = findViewById(R.id.register_BO_image);
        logOut = findViewById(R.id.singUp_LogOut);
        sales = findViewById(R.id.imageButton6);
        pd = new ProgressDialog(this);
        controller = new businessActivityController(this);
        controller.getUserNameController();
        controller.getImageProfile();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(businessActivityView.this, viewClient.class));
                finish();
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(businessActivityView.this, addUserView.class));
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(businessActivityView.this, editBusinessView.class));
            }
        });
        sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(businessActivityView.this, saleView.class));
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(businessActivityView.this, MainActivityView.class);
                startActivity(intent);
                finish();
                Toast.makeText(businessActivityView.this , "Logout Successful",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setUserName(String name){
        title.setText("Hello "+name);
    }
    public void setToastView(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    public void setPdView(String msg){
        pd.setTitle(msg);
        pd.show();
    }
    public void pdDismissView(){
        pd.dismiss();
    }

    public void picassoView(Uri uri){
        Picasso.get().load(uri).into(profilePic);
    }

    public void setImage(Business user) {
        Picasso.get().load(user.getImage()).placeholder(R.drawable.vetuser).into(profilePic);
    }
}
