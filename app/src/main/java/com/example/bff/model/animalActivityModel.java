package com.example.bff.model;

import android.net.Uri;


import androidx.annotation.NonNull;
import androidx.dynamicanimation.animation.SpringAnimation;

import com.example.bff.R;
import com.example.bff.controller.animalActivityController;
import com.example.bff.entities.Notification;
import com.example.bff.entities.User;
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
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class animalActivityModel {
    public String userName;
    private FirebaseAuth fAuth;
    private StorageReference storageReference;
    private FirebaseStorage storage;
    animalActivityController controller;
    private DatabaseReference reference;
    DatabaseReference reff ;
    String id;


    public animalActivityModel(animalActivityController controller, String id) {
        this.id = id;
        this.storage = FirebaseStorage.getInstance();
        this.storageReference = storage.getReference();
        this.fAuth = FirebaseAuth.getInstance();
        this.controller = controller;
        this.reference = FirebaseDatabase.getInstance().getReference("Users");
        this.reff = FirebaseDatabase.getInstance().getReference().child("Users").child(id).child("news");
    }

    public void imageListener() {
        StorageReference riversRef = storageReference.child("Users").child(id);
        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                controller.setImageController(uri);
            }
        });
    }

    public void logOut_model() {
        FirebaseAuth.getInstance().signOut();
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
                        reference.child(id).updateChildren(obj).addOnSuccessListener(new OnSuccessListener<Void>() {
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

    public void getUserNameModel() {
        FirebaseDatabase.getInstance().getReference().child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                controller.setNameController(user.getUsername());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void getImageProfileModel() {
        FirebaseDatabase.getInstance().getReference().child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                controller.setImage(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void GetNews(ArrayList<Notification> lst) {
        this.reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot dataSnapshot: snapshot.getChildren())
                    {
                        Notification notification = dataSnapshot.getValue(Notification.class);
                        if(notification.getStatus().equals("new")) {
                            lst.add(notification);
                        }

                    }
                    controller.SetNews(lst);
                    reff.removeEventListener(this);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void  SetNewsToOld(ArrayList<Notification> lst){
        for(Notification n: lst){
            n.setStatus("old");
            FirebaseDatabase.getInstance().getReference().child("Users").child(id).child("news").child(n.getId()).setValue(n).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                    }
                }
            });
        }
    }
}