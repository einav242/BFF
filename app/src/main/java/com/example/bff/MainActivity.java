package com.example.bff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button singupAnimal;
    private Button login;
    private Button singupBusiness;
    private Button forget;
    String permit = "empty";
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        singupAnimal = findViewById(R.id.button3);
        login = findViewById(R.id.button2);
        singupBusiness = findViewById(R.id.button4);
        forget = findViewById(R.id.button);
        EditText user_name = findViewById(R.id.editTextName);
        EditText password = findViewById(R.id.edtTxt2);
        mAuth = FirebaseAuth.getInstance();

        singupAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Register.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
        singupBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, register_BusinessOwner.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, forget_password.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = user_name.getText().toString();
                String passwordE = password.getText().toString();
                if (MainActivity.this.permit == "empty") {
                    TextView txtFirstName = findViewById(R.id.textView4);
                    txtFirstName.setText("Please select a permission");
                    txtFirstName.setTextColor(Color.RED);
                }
                if (TextUtils.isEmpty(username)) {
                    user_name.setError("Email cannot be empty");
                    user_name.requestFocus();
                } else if (TextUtils.isEmpty(passwordE)) {
                    password.setError("Password cannot be empty");
                    password.requestFocus();
                } else{
                    mAuth.signInWithEmailAndPassword(username, passwordE).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                if (MainActivity.this.permit == "animal") {
                                    Toast.makeText(MainActivity.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(MainActivity.this, animalActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                    finish();
                                } else if (MainActivity.this.permit == "business") {
                                    Toast.makeText(MainActivity.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(MainActivity.this, businessActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                    finish();

                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
                }
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
}




