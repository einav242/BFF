package com.example.bff.model;


import android.net.Uri;


import androidx.annotation.NonNull;

import com.example.bff.controller.editBusinessController;
import com.example.bff.entities.Business;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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


import java.util.Date;
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
    private FirebaseDatabase database;

    public editBusinessModel(editBusinessController controller) {
        this.controller = controller;
        this.mAuth = FirebaseAuth.getInstance().getCurrentUser();
        this.fAuth =  FirebaseAuth.getInstance();
        this.reference = FirebaseDatabase.getInstance().getReference("Business");
        this.storage = FirebaseStorage.getInstance();
        this.storageReference = storage.getReference();
        this.database = FirebaseDatabase.getInstance();

    }
    public void getImageModel(){
        StorageReference riversRef = storageReference.child("Business").child(mAuth.getUid());
        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
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
        controller.setPdController("Uploading Image...");
        long time = new Date().getTime();
        StorageReference riversRef = storageReference.child("Profile").child(time + "");
        riversRef.putFile(imageUri).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
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
                        String filePath = uri.toString();
                        HashMap<String, Object> obj = new HashMap<>();
                        obj.put("image", filePath);
                        reference.child(fAuth.getCurrentUser().getUid()).updateChildren(obj).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                controller.setImageController(imageUri);
                                controller.setToastController("Image Upload");
                            }
                        });
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


    public void updateImageModel(Uri imageUri) {
        if(imageUri != null){
            StorageReference reference = storageReference.child("Business").child(mAuth.getUid());
            reference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if(task.isSuccessful()){
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String i = uri.toString();
                                database.getReference().child("Business").child(mAuth.getUid()).child("image").setValue(i).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        controller.pdDismissController();
                                        controller.setToastController("Successfully Save Image");

                                    }
                                });
                            }
                        });
                    }
                }
            });
        }
    }
}