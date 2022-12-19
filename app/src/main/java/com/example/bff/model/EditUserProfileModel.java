package com.example.bff.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.bff.entities.User;
import com.example.bff.controller.EditUserProfileController;
import com.example.bff.view.EditUserProfileView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class EditUserProfileModel {

    private EditUserProfileController controller;
    private FirebaseUser mAuth;
    private DatabaseReference reference; //for the database that save already
    private FirebaseAuth fAuth;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    public EditUserProfileModel(EditUserProfileController controller) {
        this.controller = controller;
        this.mAuth = FirebaseAuth.getInstance().getCurrentUser();
        this.fAuth =  FirebaseAuth.getInstance();
        this.reference = FirebaseDatabase.getInstance().getReference("Users");
        this.storage = FirebaseStorage.getInstance();
        this.storageReference = storage.getReference();

    }
    public void getImageModel(){
        StorageReference profileRef = storageReference.child("user/"+ Objects.requireNonNull(fAuth.getCurrentUser()).getUid() +"profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                controller.setImageController(uri);
            }
        });
    }

    public void getDataModel(){
        FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                controller.setDataController(user.getName(),user.getUsername(),user.getEmail(),user.getPhone());

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updateModel(String newFullName, String newAnimalName, String newPhone) {
        FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                HashMap hashMap = new HashMap();
                hashMap.put("name",newFullName);
                hashMap.put("username",newAnimalName);
                hashMap.put("email", user.getEmail());
                hashMap.put("flag","animal");
                hashMap.put("id",user.getId());
                hashMap.put("phone",newPhone);
                reference.child(mAuth.getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {@Override
                    public void onSuccess(Object o) {
                        controller.setToastController("your Data is successfully Update");
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void uploadPicture_model(Uri imageUri) {
        //uplaod image to firebase storage
//        ImageView profilePic = this.profilePic;
        controller.setPdController("Uploading Image...");

        StorageReference riversRef = storageReference.child("user/" + Objects.requireNonNull(fAuth.getCurrentUser()).getUid() + "profile.jpg");
        // Register observers to listen for when the download is done or if it fails
        riversRef.putFile(imageUri).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                controller.pdDismissController();
                controller.setToastController("Failed To Upload");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                controller.pdDismissController();
                riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

                    @Override
                    public void onSuccess(Uri uri) {
                        controller.setImageController(uri);
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                double progressPercent = (100.00 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                controller.setPdController("Percentage: " + (int) progressPercent + "%");
            }
        });


    }



}
