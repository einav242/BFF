package com.example.bff.model;


import androidx.annotation.NonNull;

import com.example.bff.controller.ClientAdapterController;
import com.example.bff.entities.queue;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;



public class ClientAdapterModel {
    ClientAdapterController controller;

    public ClientAdapterModel(ClientAdapterController clientAdpterController) {
        this.controller=clientAdpterController;
    }



    public void SendModel(queue q, String id) {
        FirebaseDatabase.getInstance().getReference().child("queue").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(id).setValue(q).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    controller.goneViewController();
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

