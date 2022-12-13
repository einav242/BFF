package com.example.bff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Objects;

public class get_lost extends AppCompatActivity
{

    private FirebaseAuth mAuth;
    private Button addLost;
    private Button lostView;
    private Button found;
    FirebaseUser userF;



    DatabaseReference mRootRef;
    ProgressDialog pd;


    private FirebaseAuth fAuth;
    private ImageView profilePic;
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_lost);

        mRootRef = FirebaseDatabase.getInstance().getReference();
        addLost = findViewById(R.id.get_lost_IgotLost);
        lostView = findViewById(R.id.get_lost_viewlost);
        mAuth = FirebaseAuth.getInstance();
        profilePic = findViewById(R.id.imageButton);
        found = findViewById(R.id.get_lost_foundMe);

        userF = FirebaseAuth.getInstance().getCurrentUser();


        //try add image
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

//        found.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                if (userF.equals("")){
////                    Toast.makeText(get_lost.this, "Somthing went wrong!"+
////                            "User Derails are not availble at the moment" , Toast.LENGTH_SHORT).show();
////                    Intent intent = new Intent(get_lost.this,animalActivity.class);
////                    startActivity(intent);
////                    finish();
////                }
////                else{
//                    FirebaseDatabase.getInstance().getReference().child("Got Lost")
//                            .child(mAuth.getUid()).setValue(null)
//                            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void unused) {
//                                    mAuth.getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//                                            if(task.isSuccessful()){
//                                                pd.dismiss();
//                                                Intent intent = new Intent(get_lost.this,animalActivity.class);
//                                                startActivity(intent);
//
//                                            }
//
//                                        }
//                                    });
//                                }
//                            });
//
//            }
//        });


        found.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase.getInstance().getReference().child("Got Lost").child(mAuth.getUid())
                        .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        String email = user.getEmail();
                        String id = user.getId();
                        String phone = user.getPhone();
                        String username = user.getUsername();
                        deleteArtist(id);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });


        lostView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(get_lost.this, list_gotLost.class));
            }
        });



        addLost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        String email = user.getEmail();
                        String phone = user.getPhone();
                        String id = user.getId();
                        String username = user.getUsername();
                        InserData(phone, email , username,id);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }

        });


    }

    private void showToast(String massage){
        Toast.makeText(this,massage,Toast.LENGTH_LONG).show();
    }

    private void deleteArtist(String id) {
         DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Got Lost").child(id);
//         databaseReference.removeValue();
         Task<Void> mTask = databaseReference.removeValue();
         mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
             @Override
             public void onSuccess(Void unused) {
                showToast("Deleted");
             }
         }).addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                 showToast("Error delete");
             }
         });


        Toast.makeText(get_lost.this, "User Details delete", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(get_lost.this,animalActivity.class);
        startActivity(intent);



    }


    private void InserData(String phone,final String email,String username,String id) {
 //       String id = mRootRef.push().getKey();
        User user = new User(phone,email,username);
//        StorageReference riversRef = storageReference.child("user/"+ Objects.requireNonNull(fAuth.getCurrentUser()).getUid() +"profile.jpg");
        mRootRef.child("Got Lost").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    pd.dismiss();
                    Toast.makeText(get_lost.this, "User Details Inserted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(get_lost.this,animalActivity.class);
                    startActivity(intent);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(get_lost.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(get_lost.this,animalActivity.class);
                startActivity(intent);
            }
        });
    }


//    private void InserData(String phone,final String email,String username) {
//        String id = mRootRef.push().getKey();
//        User user = new User(phone,email,username);
////        StorageReference riversRef = storageReference.child("user/"+ Objects.requireNonNull(fAuth.getCurrentUser()).getUid() +"profile.jpg");
//        mRootRef.child("Got Lost").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()) {
//                    pd.dismiss();
//                    Toast.makeText(get_lost.this, "User Details Inserted", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(get_lost.this,animalActivity.class);
//                    startActivity(intent);
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                pd.dismiss();
//                Toast.makeText(get_lost.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(get_lost.this,animalActivity.class);
//                startActivity(intent);
//            }
//        });
//    }


}