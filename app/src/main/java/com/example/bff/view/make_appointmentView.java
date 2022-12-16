package com.example.bff.view;

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

import com.example.bff.Business;
import com.example.bff.Client;
import com.example.bff.R;
import com.example.bff.controller.make_appointmentController;
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

public class make_appointmentView extends AppCompatActivity {
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
    make_appointmentController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_appointment);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            businessID = extras.getString("key");
            businessName = extras.getString("name");
        }
        controller = new make_appointmentController(this);
        clientList=new ArrayList<>();
        controller.clientList(businessID);
        pd = new ProgressDialog(this);
        date = findViewById(R.id.editTextDate);
        time = findViewById(R.id.editTextTime);
        txt_time = time.getText().toString();
        txt_date = date.getText().toString();
        controller.getEmailController();
        send = findViewById(R.id.button9);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_time = time.getText().toString();
                txt_date = date.getText().toString();
                String id="date: "+txt_date.replace('/','-')+" hour: "+txt_time;

            }
        });
    }
    public void setListView(List<Client> clientList){
        this.clientList = clientList;
    }
    public void setEmailView(String email){
        this.email = email;
    }

}
