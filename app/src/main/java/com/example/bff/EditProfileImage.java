package com.example.bff;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ProxyInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileImage extends AppCompatActivity {

    private CircleImageView profileImageView;
    private Button closeButton,saveButton;
    private TextView profileChangeBtn;

    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    private Uri imageUri;
    private String myUri = "";
    private StorageTask UploadTask;
    private StorageReference storageProfilePicsRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_image);

        /////init
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("User");
        storageProfilePicsRef = FirebaseStorage.getInstance().getReference().child("Profile Pic");

        profileImageView = findViewById(R.id.profile_image);

        closeButton = findViewById(R.id.btnClose);
        saveButton = findViewById(R.id.btnSave);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditProfileImage.this,edit_User_Profile.class));
            }
        });


       saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadProfileImage();
            }
        });

//       profileChangeBtn.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//               intent.setType("image/*");
//               startActivityForResult(internt,Gallery_Code);
//           }
//       });


    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == )
//    }


    private void uploadProfileImage() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Set your Profile");
        progressDialog.setMessage("Please wait, while we are setting your data ");
        progressDialog.show();

        if(imageUri != null){
            final StorageReference fileRef = storageProfilePicsRef
                    .child(mAuth.getCurrentUser().getUid()+ ".jpg");

            uploadTask = fileRef.putFile(imageUri);


        }
    }
}