package com.example.bff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.Date;
import java.util.HashMap;


public class addUser extends AppCompatActivity {
    EditText name, email, phone, animalname, date;
    Button add;
    private DatabaseReference mRootRef;
    private FirebaseAuth mAuth;
    ProgressDialog pd;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_client);
        name = findViewById(R.id.editTextTextPersonName);
        email = findViewById(R.id.editTextTextEmailAddress);
        phone = findViewById(R.id.editTextPhone);
        animalname = findViewById(R.id.editTextTextPersonName2);
        date = findViewById(R.id.editTextDate);
        add =findViewById(R.id.button5);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtName = name.getText().toString();
                String txtEmail = email.getText().toString();
                String txtphone = phone.getText().toString();
                String txtAnimalName = animalname.getText().toString();
                String txtDate = date.getText().toString();

                if (TextUtils.isEmpty(txtName)
                        || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtphone)|| TextUtils.isEmpty(txtAnimalName) || TextUtils.isEmpty(txtDate)){
                    Toast.makeText(addUser.this, "Empty credentials!", Toast.LENGTH_SHORT).show();
                } else {
                    addUsersD(txtName , txtEmail , txtphone, txtAnimalName,txtDate);
                }
            }
        });
    }
    private void addUsersD(final String name, final String email, String phone,String animalName,String date){
        pd.setMessage("Please Wait!");
        pd.show();
        HashMap<String , Object> map = new HashMap<>();
        map.put("bussniessemail" , mAuth.getCurrentUser().getEmail());
        map.put("email", email);
//        map.put("phone" , phone);
//        map.put("animalName", animalName);
//        map.put("Date",date);
        mRootRef.child("Em").child(mAuth.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    pd.dismiss();
                    Toast.makeText(addUser.this, "Update the profile " +
                            "for better expereince", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(addUser.this , MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}