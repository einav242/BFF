package com.example.bff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class register_BusinessOwner extends AppCompatActivity {

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

    private Button register;
    private Button editImage;
    private ImageButton imageB;
    private TextView loginUser;

    private DatabaseReference mRootRef;
    private FirebaseAuth mAuth;

    String[] language ={"veterinary medicine","hairdressing salon","dog walker"};

    ProgressDialog pd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_business_owner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,language);
        AutoCompleteTextView actv = findViewById(R.id.register_BO_TypeOfBusiness);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.RED);



        name = findViewById(R.id.register_BO_fullName);
        businessName = findViewById(R.id.register_BO_BusinessName);
        email = findViewById(R.id.register_BO_Email);
        password = findViewById(R.id.register_BO_Password);
        id = findViewById(R.id.register_BO_ID);
        phone = findViewById(R.id.register_BO_PhoneNumber);
        city = findViewById(R.id.register_BO_City);
        street = findViewById(R.id.register_BO_Street);
        house_number = findViewById(R.id.register_BO_StreetNum);
//        type = findViewById(R.id.register_BO_TypeOfBusiness);
        register = findViewById(R.id.register_BO_Register);
        loginUser = findViewById(R.id.register_BO_LoginHere);
        imageB = findViewById(R.id.register_BO_image);
//        editImage = findViewById(R.id.register_BO_EditPhoto);

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(this);

        loginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(register_BusinessOwner.this , MainActivity.class));
            }
        });


        //if the user click on image --> he can add new image

//        imageB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });


        // the same thing like image

//        editImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

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

                if (TextUtils.isEmpty(txtUsername) || TextUtils.isEmpty(txtName)
                        || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword) || TextUtils.isEmpty(txtId)
                        || TextUtils.isEmpty(txtPhone) || TextUtils.isEmpty(txtCity) || TextUtils.isEmpty(txtStreet)
                        || TextUtils.isEmpty(txtHouseNumber) || TextUtils.isEmpty(txtType)){
                    Toast.makeText(register_BusinessOwner.this, "Empty credentials!", Toast.LENGTH_SHORT).show();
                } else if (txtPassword.length() < 6){
                    Toast.makeText(register_BusinessOwner.this, "Password too short!", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(txtUsername , txtName , txtEmail , txtPassword, txtId, txtPhone, txtCity, txtStreet, txtHouseNumber, txtType);
                }
            }
        });
    }

    private void registerUser(final String username, final String name, final String email, String password ,String id ,String phone,
                              String city , String street, String houseNumber, String type) {

        pd.setMessage("Please Wait!");
        pd.show();

        mAuth.createUserWithEmailAndPassword(email , password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                HashMap<String , Object> map = new HashMap<>();
                map.put("name" , name);
                map.put("email", email);
                map.put("username" , username);
                map.put("id" , mAuth.getCurrentUser().getUid());
                map.put("businessID", id);
                map.put("phone", phone);
                map.put("city", city);
                map.put("street", street);
                map.put("houseNumber",houseNumber);
                map.put("type",type);

                mRootRef.child("Business").child(mAuth.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            pd.dismiss();
                            Toast.makeText(register_BusinessOwner.this, "Update the profile " +
                                    "for better expereince", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(register_BusinessOwner.this , MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(register_BusinessOwner.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}