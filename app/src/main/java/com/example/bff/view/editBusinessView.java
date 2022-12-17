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

import com.example.bff.R;
import com.example.bff.entities.User;
import com.example.bff.businessActivity;
import com.example.bff.controller.editBusinessController;

public class editBusinessView extends AppCompatActivity {
    private EditText name;
    private EditText businessName;
    private TextView email;
    private EditText id;
    private EditText phone;
    private EditText city;
    private EditText street;
    private EditText house_number;
    private EditText type;
    private EditText time;
    private Button update;
    private User user;

    private ImageView profilePic;
    public Uri imageUri;
    editBusinessController controller;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_business_activity);


        user = getIntent().getParcelableExtra("key");
        controller = new editBusinessController(user,this);


        name = findViewById(R.id.register_BO_fullName);
        businessName = findViewById(R.id.register_BO_BusinessName);
        email = findViewById(R.id.register_BO_Email);
        id = findViewById(R.id.register_BO_ID);
        phone = findViewById(R.id.register_BO_PhoneNumber);
        city = findViewById(R.id.register_BO_City);
        street = findViewById(R.id.register_BO_Street);
        house_number = findViewById(R.id.register_BO_StreetNum);
        type = findViewById(R.id.register_BO_TypeOfBusiness);
        time = findViewById(R.id.editTextTextPersonName2);
        update = findViewById(R.id.register_BO_Register);
        profilePic = findViewById(R.id.register_BO_image);
        controller.EditProfileimage_controller(profilePic);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.update(name.getText().toString(),businessName.getText().toString(),id.getText().toString(),phone.getText().toString(),city.getText().toString(),street.getText().toString(),
                        house_number.getText().toString(),type.getText().toString(), time.getText().toString());
                startActivity(new Intent(editBusinessView.this, businessActivity.class));
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

}
