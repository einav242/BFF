package com.example.bff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class make_appointment extends AppCompatActivity {
    EditText time, date;
    String txt_time, txt_date;
    Button send;
    ProgressDialog pd;
    String id;
    String email;
    private DatabaseReference mRootRef;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_appointment);
        pd = new ProgressDialog(this);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        date = findViewById(R.id.editTextDate);
        time = findViewById(R.id.editTextTime);
        txt_time = time.getText().toString();
        txt_date = date.getText().toString();
        FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Business user = dataSnapshot.getValue(Business.class);
                email = user.getEmail();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        send = findViewById(R.id.button9);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.show();
                id=mRootRef.push().getKey();
                txt_time = time.getText().toString();
                txt_date = date.getText().toString();
                Client client=new Client(email,txt_date,txt_time);
                FirebaseDatabase.getInstance().getReference().child("Em").child(mAuth.getUid()).child(id).setValue(client).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            pd.dismiss();
                            Toast.makeText(make_appointment.this,"send message",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

}