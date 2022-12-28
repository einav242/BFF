//package com.example.bff.model;
//
//import android.net.Uri;
//import android.widget.ImageView;
//
//import com.example.bff.controller.AnimalAdapterController;
//import com.example.bff.controller.EditUserProfileController;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//
//import java.util.Objects;
//
//public class AnimalAdapterModel {
//
//    private AnimalAdapterController controller;
//    private FirebaseUser mAuth;
//    private DatabaseReference reference; //for the database that save already
//    private FirebaseAuth fAuth;
//    private FirebaseStorage storage;
//    private StorageReference storageReference;
//
//
//
//    public void getImageModel() {
//        StorageReference profileRef = storageReference.child("user/"+ Objects.requireNonNull(fAuth.getCurrentUser()).getUid() +"profile.jpg");
//        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                controller.setImageController(uri);
//            }
//        });
//    }
//}
