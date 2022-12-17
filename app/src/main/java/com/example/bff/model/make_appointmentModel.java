package com.example.bff.model;


import androidx.annotation.NonNull;

import com.example.bff.entities.Business;
import com.example.bff.entities.Client;
import com.example.bff.controller.make_appointmentController;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class make_appointmentModel {
    make_appointmentController controller;
    private DatabaseReference mRootRef;
    private FirebaseAuth mAuth;
    int flag=0;

    public make_appointmentModel(make_appointmentController controller) {
        this.controller = controller;
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }

    public void getEmailModel(){
        FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Business user = dataSnapshot.getValue(Business.class);
                controller.setEmailController(user.getEmail());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void sendModel(String email, String txt_date, String txt_time, String id, String businessID){
        FirebaseDatabase.getInstance().getReference().child("Em").child(businessID).child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    controller.setToastController("The queue is currently occupied");
                    flag=1;
                }
                else
                {
                    Client client=new Client(email,txt_date,txt_time,"waiting");
                    FirebaseDatabase.getInstance().getReference().child("Em").child(businessID).child(id).setValue(client).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                controller.setPdController();
                                controller.setToastController("send message");
                            }

                        }
                    });
                    flag = 0;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
