package com.example.bff.model;

import androidx.annotation.NonNull;
import com.example.bff.User;
import com.example.bff.controller.MainActivityController;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivityModel {
    FirebaseAuth mAuth;
    private DatabaseReference mData;
    private MainActivityController controller;
    String permit;
    public MainActivityModel(MainActivityController controller) {
        mAuth = FirebaseAuth.getInstance();
        this.controller = controller;
        permit = "empty";
    }

    public void login( String username, String  passwordE, String permit){
        this.permit = permit;
        mAuth.signInWithEmailAndPassword(username, passwordE).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    checkUserAccesLevel(task.getResult().getUser().getUid());
                } else {
                    controller.toast_controller("Log in Error: " + task.getException().getMessage());
                }
            }

        });
    }

    private void checkUserAccesLevel(String uid) {
        String permit = this.permit;
        if(permit=="animal") {
            mData = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        }
        if(permit=="business"){
            mData = FirebaseDatabase.getInstance().getReference().child("Business").child(uid);
        }
        mData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    User user =  snapshot.getValue(User.class);
                    controller.passActivity_controller(user);
                }
                else{
                    controller.toast_controller("Log in Error: User Not Exist at that section");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
