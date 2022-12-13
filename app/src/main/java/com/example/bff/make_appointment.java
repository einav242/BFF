package com.example.bff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class make_appointment extends AppCompatActivity {
    EditText time, date;
    String txt_time, txt_date;
    String businessID;
    String businessName;
    Button send;
    ProgressDialog pd;
    List<Client> clientList;
    int flag=0;
   static int id=1;
    String email;
    private DatabaseReference mRootRef;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_appointment);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            businessID = extras.getString("key");
            businessName = extras.getString("name");
        }
        clientList=new ArrayList<>();
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
                txt_time = time.getText().toString();
                txt_date = date.getText().toString();
                String id="date: "+txt_date.replace('/','-')+" hour: "+txt_time;
                FirebaseDatabase.getInstance().getReference().child("Em").child(businessID).child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists())
                        {
                            Toast.makeText(make_appointment.this,"The queue is currently occupied",Toast.LENGTH_SHORT).show();
                            flag=1;
                        }
                        else
                        {
                            flag = 0;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        if(flag==0)
                        {
                            Client client=new Client(email,txt_date,txt_time,"waiting");
                            FirebaseDatabase.getInstance().getReference().child("Em").child(businessID).child(id).setValue(client).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        pd.dismiss();
                                        Toast.makeText(make_appointment.this,"send message",Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                        }

                    }
                };
                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(runnable,300);
            }
        });
    }

}
