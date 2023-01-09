package com.example.bff.model;


import androidx.annotation.NonNull;

import com.example.bff.controller.ClientAdapterController;
import com.example.bff.entities.Notification;
import com.example.bff.entities.User;
import com.example.bff.entities.queue;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ClientAdapterModel {
    ClientAdapterController controller;

    public ClientAdapterModel(ClientAdapterController clientAdpterController) {
        this.controller=clientAdpterController;
    }

    public void setNotification(String id, String businessName, String userID){
        Notification note=new Notification("You got new answer from "+businessName, id);
        FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("news").child(id).setValue(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }

    public void findID(String email, String id, String businessName){
        FirebaseDatabase.getInstance().getReference().child("Users").addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    User user = dataSnapshot.getValue(User.class);
                    if(user.getEmail()!=null){
                        if(user.getEmail().equals(email)){
                            setNotification(id,businessName,user.getId());
                            break;
                        }
                    }

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




    public void SendModel(queue q, String id) {
        FirebaseDatabase.getInstance().getReference().child("queue").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(id).setValue(q).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    controller.goneViewController();
                    findID(q.getEmail(),id,q.getBusinessName());
                }
                else {}

            }
        });
    }


    public void SendModelDelete(queue q,String id) {
        FirebaseDatabase.getInstance().getReference("queue").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    controller.goneViewController();
                }
                else {}
            }
        });
        FirebaseDatabase.getInstance().getReference("History").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(id).setValue(q).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    controller.goneViewController();
                }
                else {}
            }
        });
    }

}

