package com.example.bff.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bff.MainActivity;
import com.example.bff.R;
import com.example.bff.User;
import com.example.bff.controller.animalActivity_controller;
import com.example.bff.edit_User_Profile;
import com.example.bff.get_lost;
import com.example.bff.information_page;
import com.example.bff.search;
import com.example.bff.viewQueue;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class animalActivityView extends AppCompatActivity {
    private TextView title;
    private ImageButton information;
    private ImageButton edit;
    private ImageButton search;
    private ImageButton view;
    private ImageButton getlost;
    private Button logOut;
    private animalActivity_controller controller;
    private String userName;
    private HashMap<String, String> businessNames;
    public Uri imageUri;
    private User user;

    private ImageView profilePic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animalwindow);
        user = getIntent().getParcelableExtra("key");
        controller = new animalActivity_controller(user,this);
        businessNames = controller.getbusinessName_controller();
        getlost = findViewById(R.id.imageButton2);
        information = findViewById(R.id.imageButton10);
        edit = findViewById(R.id.imageButton8);
        title = findViewById(R.id.txtMessage);
        profilePic = findViewById(R.id.edit_user_image);
        search = findViewById(R.id.imageButton7);
        logOut = findViewById(R.id.singUp_LogOut);
        view = findViewById(R.id.imageButton13);

        title.setText("Hello "+user.getUsername());
 ;
        controller.imageListener_controller(profilePic);

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open gallery
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI) ;
                startActivityForResult(openGalleryIntent,1000);
            }
        });

        getlost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(animalActivityView.this, get_lost.class));
            }
        });

        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(animalActivityView.this, information_page.class));
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(animalActivityView.this, edit_User_Profile.class));

            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(animalActivityView.this, search.class));

            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.logOut_controller();
                Intent intent = new Intent(animalActivityView.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(animalActivityView.this , "Logout Successful",Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(animalActivityView.this, viewQueue.class);
                intent.putExtra("key", businessNames);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1000 && resultCode==RESULT_OK ){
            imageUri = data.getData();
            //profilePic.setImageURI(imageUri);
            controller.uploadPicture_controller(imageUri);
        }
    }

    public animalActivityView() {

    }

    public void setUserName(String name)
    {
        this.userName = name;
    }

}



