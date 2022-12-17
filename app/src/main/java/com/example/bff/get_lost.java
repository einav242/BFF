package com.example.bff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bff.entities.User;
import com.example.bff.view.animalActivityView;
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
import com.google.firebase.storage.StorageReference;

public class get_lost extends AppCompatActivity
{

    private FirebaseAuth mAuth;
    private Button addLost;
    private Button lostView;
    private Button found;
    private int flag=0;
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


        pd = new ProgressDialog(this);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        addLost = findViewById(R.id.get_lost_IgotLost);
        lostView = findViewById(R.id.get_lost_viewlost);
        mAuth = FirebaseAuth.getInstance();
        profilePic = findViewById(R.id.imageButton);
        found = findViewById(R.id.get_lost_foundMe);

        userF = FirebaseAuth.getInstance().getCurrentUser();



        // if they found me
        found.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference("Got Lost").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            User user = snapshot.getValue(User.class);
                            String id = user.getId();
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Got Lost").child(id);
                            databaseReference.removeValue();
                            Toast.makeText(get_lost.this, "User Details delete", Toast.LENGTH_SHORT).show();
                            finish();
//                            Intent intent = new Intent(get_lost.this,animalActivityView.class);
//                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(get_lost.this,"Your animal not on the list",Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(get_lost.this,animalActivityView.class);
//                            startActivity(intent);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        //view all dog and cat that got lost
        lostView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(get_lost.this, list_gotLost.class));
            }
        });


        //add lost pet
        addLost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("Got Lost").child(mAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists())
                        {
                            Toast.makeText(get_lost.this,"Your animal is already listed",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    User user = dataSnapshot.getValue(User.class);
                                    String email = user.getEmail();
                                    String phone = user.getPhone();
                                    String id = user.getId();
                                    String username = user.getName();
                                    InserData(phone, email , username,id);
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
//                Runnable runnable = new Runnable() {
//                    @Override
//                    public void run() {
//                        if(flag==0)
//                        {
//                            FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                    User user = dataSnapshot.getValue(User.class);
//                                    String email = user.getEmail();
//                                    String phone = user.getPhone();
//                                    String id = user.getId();
//                                    String username = user.getUsername();
//                                    InserData(phone, email , username,id);
//                                }
//                                @Override
//                                public void onCancelled(@NonNull DatabaseError error) {
//                                }
//                            });
//                        }
//                    }
//                };
//                Handler handler = new Handler(Looper.getMainLooper());
//                handler.postDelayed(runnable,300);
                finish();
            }

        });


    }


    private void deleteArtist(String id) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Got Lost").child(id);
        Task<Void> mTask = databaseReference.removeValue();
        mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(get_lost.this, "User Details delete", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(get_lost.this,animalActivityView.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(get_lost.this, "Faild", Toast.LENGTH_SHORT).show();
            }
        });

        databaseReference.removeValue();

//        Toast.makeText(get_lost.this, "User Details delete", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(get_lost.this,animalActivity.class);
//        startActivity(intent);

    }


    private void InserData(String phone,final String email,String username,String id) {
//        FirebaseDatabase.getInstance().getReference("Got Lost").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
//                                                                                                                @Override
//                                                                                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                                                                                                    if (snapshot.exists()){
//                                                                                                                        Toast.makeText(get_lost.this,"Your animal is already registered as lost",Toast.LENGTH_SHORT).show();
//                                                                                                                        Intent intent = new Intent(get_lost.this,animalActivity.class);
//                                                                                                                        startActivity(intent);
//                                                                                                                    }
//                                                                                                                    else {
//                                                                                                                        User user = new User(phone,email,username,id);
//                                                                                                                        mRootRef.child("Got Lost").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                                                                                            @Override
//                                                                                                                            public void onComplete(@NonNull Task<Void> task) {
//                                                                                                                                if (task.isSuccessful()) {
//                                                                                                                                    pd.dismiss();
//                                                                                                                                    Toast.makeText(get_lost.this, "User Details Inserted", Toast.LENGTH_SHORT).show();
//                                                                                                                                    Intent intent = new Intent(get_lost.this,animalActivity.class);
//                                                                                                                                    startActivity(intent);
//                                                                                                                                }
//                                                                                                                            }
//                                                                                                                        }).addOnFailureListener(new OnFailureListener() {
//                                                                                                                            @Override
//                                                                                                                            public void onFailure(@NonNull Exception e) {
//                                                                                                                                pd.dismiss();
//                                                                                                                                Toast.makeText(get_lost.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                                                                                                                                Intent intent = new Intent(get_lost.this,animalActivity.class);
//                                                                                                                                startActivity(intent);
//                                                                                                                            }
//                                                                                                                        });
//                                                                                                                    }
//                                                                                                                }
//                                                                                                                @Override
//                                                                                                                public void onCancelled(@NonNull DatabaseError error) {
//                                                                                                                }
//                                                                                                            });



        User user = new User(phone,email,username,id);
        mRootRef.child("Got Lost").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(get_lost.this, "User Details Inserted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(get_lost.this, animalActivityView.class);
                    intent.putExtra("key", user);
                    startActivity(intent);
                    pd.dismiss();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(get_lost.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(get_lost.this,animalActivityView.class);
                startActivity(intent);
                finish();
            }
        });
    }




}