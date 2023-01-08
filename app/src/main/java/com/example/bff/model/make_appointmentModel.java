package com.example.bff.model;


import androidx.annotation.NonNull;

import com.example.bff.entities.Business;
import com.example.bff.controller.make_appointmentController;
import com.example.bff.entities.Notfiaction;
import com.example.bff.entities.User;
import com.example.bff.entities.queue;
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
    private FirebaseAuth mAuth;

    public make_appointmentModel(make_appointmentController controller) {
        this.controller = controller;
        mAuth = FirebaseAuth.getInstance();
    }

    public void getEmailModel(){
        FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                controller.setEmailController(user);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void getBusinessName(){

    }

    public void sendModel(String email, String txt_date, String txt_time, String id, String businessID, String businessName, String image, String userImage){
        FirebaseDatabase.getInstance().getReference().child("queue").child(businessID).child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                queue q = snapshot.getValue(queue.class);;
                if(!snapshot.exists() || (snapshot.exists() && q.getStatus().equals("decline")))
                {
                    q = new queue(businessName,txt_date,txt_time, "waiting",email,image,userImage);
                    FirebaseDatabase.getInstance().getReference().child("queue").child(businessID).child(id).setValue(q).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                controller.setPdController();
                                controller.setToastController("send message");
                            }
                        }
                    });
                    Notfiaction note=new Notfiaction("You got new appointment from"+email);
                    FirebaseDatabase.getInstance().getReference().child("Business").child(businessID).child("news").setValue(note).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });


                }
                else
                {
                    controller.setToastController("The queue is currently occupied");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
