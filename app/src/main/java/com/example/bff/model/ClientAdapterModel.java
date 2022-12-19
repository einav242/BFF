package com.example.bff.model;


import androidx.annotation.NonNull;

import com.example.bff.controller.ClientAdapterController;
import com.example.bff.entities.Client;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;



public class ClientAdapterModel {
    ClientAdapterController controller;

    public ClientAdapterModel(ClientAdapterController clientAdpterController) {
        this.controller=clientAdpterController;
    }



    public void SendModelAprove(Client c, String id) {
        FirebaseDatabase.getInstance().getReference().child("Em").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(id).setValue(c).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    controller.goneViewController();
                }
                else {}

            }
        });
    }

    public void SendModelDecline(Client c, String id) {
        FirebaseDatabase.getInstance().getReference().child("Em").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(id).setValue(c).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    controller.goneViewController();
                }
                else {}

            }
        });
    }

    public void SendModelDelete(String id) {
        FirebaseDatabase.getInstance().getReference("Em").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
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
