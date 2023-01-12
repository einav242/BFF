package com.example.bff.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import com.example.bff.R;
import com.example.bff.controller.make_appointmentController;
import com.example.bff.entities.User;

import java.util.Calendar;

public class make_appointmentView extends AppCompatActivity {
    EditText time, date;
    String txt_time, txt_date;
    String businessID;
    String businessName;
    String businessImage;
    String userImage;
    Button send;
    ProgressDialog pd;
    String email;
    make_appointmentController controller;
    int mYear, mMonth, mDay, mHour, mMinute;

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
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(make_appointmentView.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                time.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(make_appointmentView.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
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
                controller.sendController(email,txt_date,txt_time,id,businessID,businessName,businessImage, userImage);
                Intent intent = new Intent(make_appointmentView.this, seeMoreView.class);
                intent.putExtra("key",businessID);
                startActivity(intent);
            }
        });
    }

    public void setEmailView(User user){
        this.email = user.getEmail();
        this.userImage = user.getImage();
    }

    public void setToastView(String msg){
        Toast.makeText(make_appointmentView.this,msg,Toast.LENGTH_SHORT).show();
    }
    public void setPd(){
        pd.dismiss();
    }
}

