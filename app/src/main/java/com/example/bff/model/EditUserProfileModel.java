package com.example.bff.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.bff.entities.User;
import com.example.bff.controller.EditUserProfileController;
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

import java.util.Objects;
import java.util.UUID;

public class EditUserProfileModel {

    private EditUserProfileController controller;
    private FirebaseUser mAuth;
    DatabaseReference reference; //for the database that save already
    private FirebaseAuth fAuth;
    private ImageView profilePic;
    private Context context;
    DatabaseReference databaseReference;


    private FirebaseStorage storage;
    private StorageReference storageReference;


    public EditUserProfileModel(EditUserProfileController controller) {
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        fAuth =  FirebaseAuth.getInstance();
        this.controller = controller;
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
    }

    public void update(String edfullName, String edAnimalName, String edPhone) {
        FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                String fullName = user.getName();
                String animalName = user.getUsername();
                String phone = user.getPhone();
                if (!fullName.equals(edfullName)) {
                    reference.child(mAuth.getUid()).child("name").setValue(edfullName);
                }
                if (!animalName.equals(edAnimalName)) {
                    reference.child(mAuth.getUid()).child("username").setValue(edAnimalName);
                }
                if (!phone.equals(edPhone)) {
                    reference.child(mAuth.getUid()).child("phone").setValue(edPhone);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    public void EditUserimage_controller(ImageView profilePic) {
//        this.profilePic = profilePic;
        StorageReference profileRef = storageReference.child("user/"+ Objects.requireNonNull(fAuth.getCurrentUser()).getUid() +"profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profilePic);
            }
        });
    }

    public void EditProfileimage_model(Uri imageUri) {
        //uplaod image to firebase storage
        ImageView profilePic = this.profilePic;
        final ProgressDialog pd = new ProgressDialog(context);
        pd.setTitle("Uploading Image...");
        pd.show();


        final  String randomKey = UUID.randomUUID().toString();


        StorageReference riversRef = storageReference.child("user/"+ Objects.requireNonNull(fAuth.getCurrentUser()).getUid() +"profile.jpg");
        // Register observers to listen for when the download is done or if it fails
        riversRef.putFile(imageUri).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                pd.dismiss();
                Toast.makeText(context, "Failed To Upload", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                pd.dismiss();
                riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(profilePic);
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                double progressPercent = (100.00 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                pd.setMessage("Percentage: " + (int)progressPercent + "%");
            }
        });


    }

    public void setDataModel(String email) {
        databaseReference.child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                controller.setDataController(user);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
