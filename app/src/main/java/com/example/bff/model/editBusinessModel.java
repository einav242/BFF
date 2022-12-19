package com.example.bff.model;


import android.net.Uri;


import androidx.annotation.NonNull;

import com.example.bff.controller.editBusinessController;
import com.example.bff.entities.Business;
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


import java.util.EnumSet;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class editBusinessModel {

    private editBusinessController controller;
    private FirebaseUser mAuth;
    private DatabaseReference reference; //for the database that save already
    private FirebaseAuth fAuth;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    public editBusinessModel(editBusinessController controller) {
        this.controller = controller;
        this.mAuth = FirebaseAuth.getInstance().getCurrentUser();
        this.fAuth =  FirebaseAuth.getInstance();
        this.reference = FirebaseDatabase.getInstance().getReference("Business");
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
        FirebaseDatabase.getInstance().getReference().child("Business").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Business user = dataSnapshot.getValue(Business.class);
                controller.setDataController(user.getEmail(),user.getName(),user.getUsername(),user.getBusinessID(),
                        user.getPhone(),user.getCity(),user.getStreet(),user.getHouseNumber(),user.getType(),user.getTime());

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updateModel(String newName,String newBusinessName, String newId, String newPhone, String newCity, String newStreet, String newHouseNumber, String newType, String newTime) {
        FirebaseDatabase.getInstance().getReference().child("Business").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Business user = dataSnapshot.getValue(Business.class);
                HashMap hashMap = new HashMap();
                hashMap.put("name",newName);
                hashMap.put("username",newBusinessName);
                hashMap.put("businessID",newId);
                hashMap.put("city",newCity);
                hashMap.put("email", user.getEmail());
                hashMap.put("flag","business");
                hashMap.put("houseNumber",newHouseNumber);
                hashMap.put("id",user.getId());
                hashMap.put("phone",newPhone);
                hashMap.put("street",newStreet);
                hashMap.put("type",newType);
                hashMap.put("time",newTime);
                reference.child(mAuth.getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        controller.setToastController("your Data is successfully Update");
                    }
                });

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        controller.passPageController();
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