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
import com.example.bff.controller.businessActivityController;

import com.google.firebase.auth.FirebaseAuth;

import com.squareup.picasso.Picasso;


public class businessActivityView extends AppCompatActivity {
    private ImageButton view;
    private ImageButton insert;
    private ImageButton edit;
    private TextView title;
    private businessActivityController controller;
    private Button logOut;
    private ProgressDialog pd;
    private ImageView profilePic;
    public Uri imageUri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_window);
        view = findViewById(R.id.imageButton5);
        insert = findViewById(R.id.imageButton3);
        edit = findViewById(R.id.imageButton8);
        title = findViewById(R.id.txtMessage);
        profilePic = findViewById(R.id.register_BO_image);
        logOut = findViewById(R.id.singUp_LogOut);
        pd = new ProgressDialog(this);
        controller = new businessActivityController(this);
        controller.getImageController(profilePic);

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

        //for add Images
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open gallery
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI) ;
                startActivityForResult(openGalleryIntent,1000);
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

    //for add Image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1000 && resultCode==RESULT_OK ){
            imageUri = data.getData();
            //profilePic.setImageURI(imageUri);
            controller.uploadPicture(imageUri);
        }
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
}
