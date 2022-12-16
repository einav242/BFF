package com.example.bff.view;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bff.R;
import com.example.bff.Register;
import com.example.bff.User;
import com.example.bff.businessActivity;
import com.example.bff.controller.MainActivity_controller;
import com.example.bff.forget_password;
import com.example.bff.register_BusinessOwner;

public class MainActivity_view extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button singupAnimal;
    private Button login;
    private Button singupBusiness;
    private Button forget;
    EditText password;
    EditText user_name;
    TextView txtFirstName;
    String permit = "empty";
    MainActivity_controller controller;

    public MainActivity_view() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainActivity_controller(this);
        txtFirstName = findViewById(R.id.textView4);
        singupAnimal = findViewById(R.id.button3);
        login = findViewById(R.id.button2);
        singupBusiness = findViewById(R.id.button4);
        forget = findViewById(R.id.button);
        user_name = findViewById(R.id.editTextName);
        password = findViewById(R.id.edtTxt2);


        singupAnimal.setOnClickListener(v -> startActivity(new Intent(MainActivity_view.this, Register.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)));
        singupBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity_view.this, register_BusinessOwner.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity_view.this, forget_password.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = user_name.getText().toString();
                String passwordE = password.getText().toString();
                controller.check_empty(username,passwordE,permit);
            }
        });
    }
    public void onRadioButtonClicked (View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.animal:
                if (checked)
                    this.permit = "animal";
                break;
            case R.id.business:
                if (checked)
                    this.permit = "business";
                break;
        }
    }
    public void noPermit() {
        txtFirstName.setText("Please select a permission");
        txtFirstName.setTextColor(Color.RED);
    }
    public void noUserName(){
        user_name.setError("Email cannot be empty");
        user_name.requestFocus();
    }
    public void noPassword(){
        password.setError("Password cannot be empty");
        password.requestFocus();
    }
    public void  toast_view(String msg){

        Toast.makeText(MainActivity_view.this,msg, Toast.LENGTH_SHORT).show();
    }
    public void paasAnimalActivity(User user){
        Intent intent=new Intent(MainActivity_view.this, com.example.bff.view.animalActivityView.class);
        intent.putExtra("key", user);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        finish();
    }
    public void passBusinessActivity(){
        startActivity(new Intent(MainActivity_view.this, businessActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        finish();
    }
}



