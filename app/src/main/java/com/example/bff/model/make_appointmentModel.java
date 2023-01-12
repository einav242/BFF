package com.example.bff.model;


import androidx.annotation.NonNull;

import com.example.bff.controller.make_appointmentController;
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

public class make_appointmentModel {
    make_appointmentController controller;
    int count;
    String id;

    public make_appointmentModel(make_appointmentController controller, String id) {
        this.id = id;
        this.controller = controller;
    }

    public void getEmailModel(){
        FirebaseDatabase.getInstance().getReference().child("Users").child(this.id).addValueEventListener(new ValueEventListener() {
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


    public void setNotification(String email, String businessID, String id){
        Notification note=new Notification("You got new appointment from "+email, id);
        FirebaseDatabase.getInstance().getReference().child("Business").child(businessID).child("news").child(id).setValue(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
            }
        });
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
                                setNotification(email, businessID,id);
                            }
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
