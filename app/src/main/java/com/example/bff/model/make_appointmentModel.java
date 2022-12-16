package com.example.bff.model;

import android.os.Handler;
import android.os.Looper;
import android.telecom.StatusHints;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.bff.Business;
import com.example.bff.Client;
import com.example.bff.controller.make_appointmentController;
import com.example.bff.view.make_appointmentView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

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
    public void clientList(String businessID){
        List<Client> clientList =new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("Em").child(businessID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Client user = dataSnapshot.getValue(Client.class);
                clientList.add(user);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        controller.setListController(clientList);
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
//    public void sendController(String email, String txt_date,String txt_time,String id, String businessID){
//
//        FirebaseDatabase.getInstance().getReference().child("Em").child(businessID).child(id).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists())
//                {
//                    Toast.makeText(make_appointmentView.this,"The queue is currently occupied",Toast.LENGTH_SHORT).show();
//                    flag=1;
//                }
//                else
//                {
//                    flag = 0;
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                if(flag==0)
//                {
//                    Client client=new Client(email,txt_date,txt_time,"waiting");
//                    FirebaseDatabase.getInstance().getReference().child("Em").child(businessID).child(id).setValue(client).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if(task.isSuccessful()){
//                                pd.dismiss();
//                                Toast.makeText(make_appointmentView.this,"send message",Toast.LENGTH_SHORT).show();
//                            }
//
//                        }
//                    });
//                }
//
//            }
//        };
//        Handler handler = new Handler(Looper.getMainLooper());
//        handler.postDelayed(runnable,300);
//    }
}
