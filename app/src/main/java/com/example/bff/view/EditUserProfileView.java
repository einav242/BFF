package com.example.bff.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bff.Business;
import com.example.bff.R;
import com.example.bff.User;
import com.example.bff.controller.EditUserProfileController;



public class EditUserProfileView extends AppCompatActivity {
    EditText edFullName;
    EditText edAnimalName;
    EditText editPhone;
    TextView edEmail;
    private Button update;
    private User user;
    TextView tv_fullName, tv_email, tv_phone, tv_animalName;
    String id;

    private ImageView profilePic;
    public Uri imageUri;
    EditUserProfileController controller;

    String email;
    String phone;
    String fullName;
    String animalName;


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);

        user = getIntent().getParcelableExtra("key");
        controller = new EditUserProfileController(user,this);

        edFullName = findViewById(R.id.edit_user_fullName);
        edAnimalName = findViewById(R.id.edit_user_AnimalName);
        edEmail = findViewById(R.id.edit_user_Email);
        editPhone = findViewById(R.id.editTxtPhone);
        update = findViewById(R.id.edit_user_Update);
        profilePic = findViewById(R.id.edit_user_image);
        controller.EditProfileimage_controller(profilePic);

//        Bundle extras = getIntent().getExtras();
//        email = extras.getString("key");
//        fullName = extras.getString("key");
//        animalName = extras.getString("key");
//        phone = extras.getString("key");


//        email = user.getEmail();
//        fullName = user.getName();
//        animalName = user.getUsername();
//        phone = user.getPhone();

//        controller.data(email,fullName,animalName,phone);


        //for add Images
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open gallery
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI) ;
                startActivityForResult(openGalleryIntent,1000);
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.update(edFullName.getText().toString(), edAnimalName.getText().toString(), editPhone.getText().toString());
                startActivity(new Intent(EditUserProfileView.this, animalActivityView.class));
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
            controller.uploadPicture_controller(imageUri);
        }
    }

    public void setDataView(User user){
        tv_fullName.setText(user.getName());
        tv_email.setText(user.getEmail());
        tv_phone.setText(user.getPhone());
        tv_animalName.setText(user.getUsername());
    }
}
