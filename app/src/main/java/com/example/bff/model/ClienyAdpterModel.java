package com.example.bff.model;

import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bff.controller.ClientAdpterController;
import com.example.bff.entities.Client;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import android.view.View;
import android.widget.TextView;

public class ClienyAdpterModel {
    ClientAdpterController controller;

    public ClienyAdpterModel(ClientAdpterController clientAdpterController) {
        this.controller=clientAdpterController;
    }



    public void SendModelAprove(Client c, String id) {
        FirebaseDatabase.getInstance().getReference().child("Em").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(id).setValue(c).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    controller.goneViewController(c);
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
                    controller.goneViewController(c);
                }
                else {}

            }
        });
    }

    public void SendModelDelete(String id, Client c) {
        FirebaseDatabase.getInstance().getReference("Em").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    controller.goneViewController(c);
                }
                else {}
            }
        });
    }
}
