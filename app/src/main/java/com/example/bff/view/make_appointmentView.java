package com.example.bff.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.bff.R;
import com.example.bff.controller.make_appointmentController;

public class make_appointmentView extends AppCompatActivity {
    EditText time, date;
    String txt_time, txt_date;
    String businessID;
    String businessName;
    String businessImage;
    Button send;
    ProgressDialog pd;
    String email;
    make_appointmentController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_appointment);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            businessID = extras.getString("key");
            businessName = extras.getString("name");
            businessImage = extras.getString("image");
        }
        controller = new make_appointmentController(this);
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
                controller.sendController(email,txt_date,txt_time,id,businessID,businessName,businessImage);
            }
        });
    }

    public void setEmailView(String email){
        this.email = email;
    }

    public void setToastView(String msg){
        Toast.makeText(make_appointmentView.this,msg,Toast.LENGTH_SHORT).show();
    }
    public void setPd(){
        pd.dismiss();
    }
}
