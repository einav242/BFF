package com.example.bff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class get_lost extends AppCompatActivity
{

    private FirebaseAuth mAuth;
    private Button addLost;
    FirebaseUser userF;

    DataSnapshot dataSnapshot;
    DatabaseReference mRootRef;
    ProgressDialog pd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_lost);

        mRootRef = FirebaseDatabase.getInstance().getReference();
//        String email = mRootRef.child("Users").child("email").toString();
//        //String username = mRootRef.child("username").toString();

        addLost = findViewById(R.id.get_lost_IgotLost);
        mAuth = FirebaseAuth.getInstance();

        userF = FirebaseAuth.getInstance().getCurrentUser();

        addLost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InserData(userF.getUid(),userF.getEmail());
            }
        });
    }

//    private void InserData(String username, String email) {
//        String id = mRootRef.push().getKey();
//        mRootRef.child("Got Lost").child(id).setValue(email).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()) {
//                    Toast.makeText(get_lost.this, "User Details Inserted", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                pd.dismiss();
//                Toast.makeText(get_lost.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    private void InserData(String username, String email) {
        mAuth.createUserWithEmailAndPassword(email, username).addOnSuccessListener(new OnSuccessListener<AuthResult>() {

            @Override
            public void onSuccess(AuthResult authResult) {

                HashMap<String, Object> map = new HashMap<>();
                map.put("username", username);
                map.put("email", email);
                map.put("id", mAuth.getCurrentUser().getUid());
                mRootRef.child("Got Lost").child(mAuth.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            pd.dismiss();
                            Toast.makeText(get_lost.this, "User Details Inserted", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(get_lost.this, animalActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(get_lost.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}