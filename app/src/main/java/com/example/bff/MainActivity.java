package com.example.bff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnClick(View view){
        TextView txtFirstName = findViewById(R.id.textView2);
        EditText edtTxtName = findViewById(R.id.editTextName);
        txtFirstName.setText(edtTxtName.getText().toString());

        TextView txtLastName = findViewById(R.id.textView3);
        EditText edtTxtName2 = findViewById(R.id.edtTxt2);
        txtLastName.setText(edtTxtName2.getText().toString());

        TextView txtEmail = findViewById(R.id.textView4);
        EditText edtTxtName3 = findViewById(R.id.editTxt3);
        txtEmail.setText(edtTxtName3.getText().toString());
    }
}