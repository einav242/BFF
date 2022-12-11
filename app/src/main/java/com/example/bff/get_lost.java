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

public class get_lost extends AppCompatActivity {

    private FirebaseUser mAuth;
    private Button addLost;

    DataSnapshot dataSnapshot;
    private DatabaseReference mRootRef;
    ProgressDialog pd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_lost);

        mRootRef = FirebaseDatabase.getInstance().getReference();


        addLost = findViewById(R.id.get_lost_IgotLost);
        mAuth = FirebaseAuth.getInstance().getCurrentUser();

        User user = dataSnapshot.getValue(User.class);


        addLost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser(user.getName(), user.getEmail());
                finish();
            }
        });
    }

    private void registerUser(final String username,final String email) {

        String id = mRootRef.push().getKey();
        mRootRef.child("Got Lost").child(id).setValue(username).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(get_lost.this, "User Derails Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(get_lost.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
//        pd.setMessage("Please Wait!");
//        pd.show();
//
//        mAuth.createUserWithEmailAndPassword(email , password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//            @Override
//            public void onSuccess(AuthResult authResult) {
//
//                HashMap<String , Object> map = new HashMap<>();
//                map.put("email", email);
//                map.put("username" , username);
//                map.put("id" , mAuth.getCurrentUser().getUid());
//                mRootRef.child("Got Lost").child(mAuth.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()){
//                            pd.dismiss();
//                            Toast.makeText(get_lost.this, "Update the profile " +
//                                    "for better expereince", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(get_lost.this , MainActivity.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            startActivity(intent);
//                            finish();
//                        }
//                    }
//                });
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                pd.dismiss();
//                Toast.makeText(get_lost.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }

}