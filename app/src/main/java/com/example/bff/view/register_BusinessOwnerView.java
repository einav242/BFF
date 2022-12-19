package com.example.bff.view;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.bff.R;
import com.example.bff.controller.register_BusinessOwnerController;


public class register_BusinessOwnerView extends AppCompatActivity {

    private EditText name;
    private EditText businessName;
    private EditText email;
    private EditText password;
    private EditText id;
    private EditText phone;
    private EditText city;
    private EditText street;
    private EditText house_number;
    private EditText type;
    private EditText time;

    private Button register;
    private TextView loginUser;
    private register_BusinessOwnerController controller;



    String[] language ={"veterinary medicine","hairdressing salon","dog walker"};

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_business_owner);
        controller = new register_BusinessOwnerController(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,language);
        AutoCompleteTextView actv = findViewById(R.id.register_BO_TypeOfBusiness);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.BLACK);

        name = findViewById(R.id.register_BO_fullName);
        businessName = findViewById(R.id.register_BO_BusinessName);
        email = findViewById(R.id.register_BO_Email);
        password = findViewById(R.id.register_BO_Password);
        id = findViewById(R.id.register_BO_ID);
        phone = findViewById(R.id.register_BO_PhoneNumber);
        city = findViewById(R.id.register_BO_City);
        street = findViewById(R.id.register_BO_Street);
        house_number = findViewById(R.id.register_BO_StreetNum);
        time = findViewById(R.id.editTextTextPersonName);
        type = findViewById(R.id.register_BO_TypeOfBusiness);
        register = findViewById(R.id.register_BO_Register);
        loginUser = findViewById(R.id.register_BO_LoginHere);


        pd = new ProgressDialog(this);

        loginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(register_BusinessOwnerView.this , MainActivityView.class));
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtUsername = businessName.getText().toString();
                String txtName = name.getText().toString();
                String txtEmail = email.getText().toString();
                String txtPassword = password.getText().toString();
                String txtId = id.getText().toString();
                String txtPhone = phone.getText().toString();
                String txtCity = city.getText().toString();
                String txtStreet = street.getText().toString();
                String txtHouseNumber = house_number.getText().toString();
                String txtType = type.getText().toString();
                String txtTime = time.getText().toString();
                controller.registerUserController(txtUsername , txtName , txtEmail , txtPassword, txtId, txtPhone, txtCity, txtStreet, txtHouseNumber, txtType, txtTime);
            }
        });
    }

    public void setToastView(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    public void setPdView(String msg){
        pd.setTitle(msg);
        pd.show();
    }
    public void pdDismissView(){
        pd.dismiss();
    }

    public void passPage(){
        Intent intent = new Intent(register_BusinessOwnerView.this , MainActivityView.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}