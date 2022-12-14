package com.example.bff.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bff.R;
import com.example.bff.controller.EditUserProfileController;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditUserProfileView extends AppCompatActivity {

    EditText edFullName;
    EditText edAnimalName;
    EditText editPhone;
    EditText editBreed;
    EditText editColor;
    EditText editType;
    TextView edEmail;
    Button edImage;
    private Button update;
    EditUserProfileController controller;
    ProgressDialog pd;
    CircleImageView profilePic;
    String id;

//    private ImageView profilePic;
    public Uri imageUri;

    String[] language ={"Dog","Cat"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("key");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,language);
        AutoCompleteTextView actv = findViewById(R.id.getlost_type);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.BLACK);

        //user can change
        edFullName = findViewById(R.id.edit_user_fullName);
        edAnimalName = findViewById(R.id.edit_user_AnimalName);
        edEmail = findViewById(R.id.edit_user_Email);
        editPhone = findViewById(R.id.editTxtPhone);
        update = findViewById(R.id.edit_user_Update);
        profilePic = findViewById(R.id.ImageViewId);
        editBreed = findViewById(R.id.getlost_breed);
        editColor = findViewById(R.id.get_lost_color);
        editType = findViewById(R.id.getlost_type);
        edImage = findViewById(R.id.edit_user_EditPhoto);


        pd = new ProgressDialog(this);
        controller = new EditUserProfileController(this, id);
        controller.getImageController();
        controller.getDataController();

        //for add Images
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open gallery
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 1);
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.updateController(edFullName.getText().toString(), edAnimalName.getText().toString(), editPhone.getText().toString() , editBreed.getText().toString() , editColor.getText().toString() , editType.getText().toString());
                Intent intent = new Intent(EditUserProfileView.this, animalActivityView.class);
                intent.putExtra("key",id);
                startActivity(intent);
            }
        });

        edImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.updateImageController(imageUri);
                Intent intent = new Intent(EditUserProfileView.this, animalActivityView.class);
                intent.putExtra("key",id);
                startActivity(intent);
            }
        });

    }

    //for add Image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK  && data != null){
            if(data.getData() != null){
                imageUri = data.getData();
                controller.uploadPictureController(imageUri);
            }
        }
    }


    public void setDataView(String name, String username, String email, String phone , String breed , String color , String type) {
        edFullName.setText(name);
        edAnimalName.setText(username);
        edEmail.setText(email);
        editPhone.setText(phone);
        editBreed.setText(breed);
        editColor.setText(color);
        editType.setText(type);
    }

    public void setImegeView(Uri uri) {
        profilePic.setImageURI(uri);
        imageUri = uri;
        Picasso.get().load(uri).into(profilePic);
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