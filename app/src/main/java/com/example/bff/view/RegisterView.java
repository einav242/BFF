package com.example.bff.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bff.R;
import com.example.bff.controller.RegisterController;

public class RegisterView extends AppCompatActivity {

    private EditText animalName;
    private EditText name;
    private EditText email;
    private EditText password;
    private EditText phone;
    private EditText breed;
    private EditText color;
    private EditText type;
    private Button register;
    private TextView loginUser;
    ProgressDialog pd;
    RegisterController controller;

    String[] language ={"Dog","Cat"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        controller = new RegisterController(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,language);
        AutoCompleteTextView actv = findViewById(R.id.getlost_type);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.BLACK);

        animalName = findViewById(R.id.singUp_AnimalName);
        name = findViewById(R.id.singUp_fullName);
        email = findViewById(R.id.singUp_Email);
        password = findViewById(R.id.singUp_Password);
        phone = findViewById(R.id.editTextPhone);
        register = findViewById(R.id.singUp_Register);
        loginUser = findViewById(R.id.singUp_Button_LoginHere);
        breed = findViewById(R.id.getlost_breed);
        color = findViewById(R.id.get_lost_color);
        type = findViewById(R.id.getlost_type);

        pd = new ProgressDialog(this);

        // if to the user have already account
        loginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterView.this , MainActivityView.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtUsername = animalName.getText().toString();
                String txtName = name.getText().toString();
                String txtEmail = email.getText().toString();
                String txtPassword = password.getText().toString();
                String txtPhone = phone.getText().toString();
                String txtBreed = breed.getText().toString();
                String txtColor = color.getText().toString();
                String txtType = type.getText().toString();
                controller.registerUser(txtUsername , txtName , txtEmail , txtPassword, txtPhone , txtBreed , txtColor , txtType);
            }
        });

    }

    public void  toast_view(String msg){
        Toast.makeText(RegisterView.this,msg, Toast.LENGTH_SHORT).show();
    }

    public void p() {
        Intent intent = new Intent(RegisterView.this , MainActivityView.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void setPdView(String msg){
        pd.setTitle(msg);
        pd.show();
    }

    public void pdDismissView(){
        pd.dismiss();
    }

}

