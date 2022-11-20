package com.example.bff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    EditText mFullName,mEmail,mPassword;
    Button mRegisterBtn;
    TextView mloginBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mFullName = findViewById(R.id.singUp_fullName);
        mEmail = findViewById(R.id.singUp_Email);


    }
}