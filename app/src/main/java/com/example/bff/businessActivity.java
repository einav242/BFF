package com.example.bff;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class businessActivity extends AppCompatActivity {
    private ImageButton view;
    private ImageButton delete;
    private ImageButton insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_window);
        view = findViewById(R.id.imageButton5);
        delete = findViewById(R.id.imageButton4);
        insert = findViewById(R.id.imageButton3);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(businessActivity.this, Userlist.class));
            }
        });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(businessActivity.this, addUser.class));
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(businessActivity.this, deleteUser.class));
            }
        });


    }
}

