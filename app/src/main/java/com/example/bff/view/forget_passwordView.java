package com.example.bff.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bff.MainActivity;
import com.example.bff.R;
import com.example.bff.controller.forget_passwordController;

public class forget_passwordView extends AppCompatActivity {

    EditText etEmail;
    Button sendEmail;
    forget_passwordController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        getSupportActionBar().setTitle("Forget Password");
        controller = new forget_passwordController(this);
        etEmail = findViewById(R.id.et_email);
        sendEmail = findViewById(R.id.bt_forget);
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                controller.checkempty(email);
            }
        });

    }

    public void setToast(String msg){
        Toast.makeText(forget_passwordView.this,msg,Toast.LENGTH_SHORT).show();
    }
    public void errorEmail(String error){
        etEmail.setError(error);
        etEmail.requestFocus();
    }
    public void passActivityView(){
        Intent intent = new Intent(forget_passwordView.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}