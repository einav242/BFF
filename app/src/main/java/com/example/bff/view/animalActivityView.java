package com.example.bff.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

import com.example.bff.R;
import com.example.bff.controller.animalActivityController;;



public class animalActivityView extends AppCompatActivity {
    private TextView title;
    private ImageButton information;
    private ImageButton edit;
    private ImageButton search;
    private ImageButton view;
    private ImageButton getlost;
    private Button logOut;
    private animalActivityController controller;
    private String userName;
    public Uri imageUri;
    private ImageView profilePic;
    private ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animalwindow);
        controller = new animalActivityController(this);
        getlost = findViewById(R.id.imageButton2);
        information = findViewById(R.id.imageButton10);
        edit = findViewById(R.id.imageButton8);
        title = findViewById(R.id.txtMessage);
        profilePic = findViewById(R.id.edit_user_image);
        search = findViewById(R.id.imageButton7);
        logOut = findViewById(R.id.singUp_LogOut);
        view = findViewById(R.id.imageButton13);
        pd = new ProgressDialog(this);
        controller.getUserNameController();
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
                startActivity(new Intent(animalActivityView.this, gotLostView.class));
            }
        });

        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(animalActivityView.this, informationPageView.class));
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(animalActivityView.this, EditUserProfileView.class));

            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(animalActivityView.this, searchView.class));

            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.logOut_controller();
                Intent intent = new Intent(animalActivityView.this, MainActivityView.class);
                startActivity(intent);
                finish();
                Toast.makeText(animalActivityView.this , "Logout Successful",Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(animalActivityView.this, viewQueueView.class);
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
            controller.uploadPicture_controller(imageUri);
        }
    }


    public void setUserName(String name)
    {
        this.userName = name;
        title.setText("Hello "+this.userName);
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
}



