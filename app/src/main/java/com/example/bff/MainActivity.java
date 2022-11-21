package com.example.bff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button signup;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup = findViewById(R.id.button3);
        login = findViewById(R.id.button2);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , Register.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}

//    public void onBtnClick(View view){
//        TextView txtFirstName = findViewById(R.id.textView2);
//        EditText edtTxtName = findViewById(R.id.editTextName);
//        txtFirstName.setText(edtTxtName.getText().toString());
//
//        TextView txtLastName = findViewById(R.id.LineDontTach);
//        EditText edtTxtName2 = findViewById(R.id.edtTxt2);
//        txtLastName.setText(edtTxtName2.getText().toString());
//
//        TextView txtEmail = findViewById(R.id.textView4);
//        EditText edtTxtName3 = findViewById(R.id.editTxt3);
//        txtEmail.setText(edtTxtName3.getText().toString());
//    }
