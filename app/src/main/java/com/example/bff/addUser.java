package com.example.bff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addUser extends AppCompatActivity {
    EditText name, email, id;
    Button add;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_client);
        name = findViewById(R.id.editTextTextPersonName);
        email = findViewById(R.id.editTextTextEmailAddress);
        id = findViewById(R.id.editTextTextPersonName3);
        add =findViewById(R.id.button5);
        DB = new DBHelper(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                String emailTXT = email.getText().toString();
                String idTXT = id.getText().toString();

                Boolean checkinsertdata  = DB.insertuserdata(nameTXT, emailTXT, idTXT);
                if(checkinsertdata==true)
                {
                    Toast.makeText(addUser.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(addUser.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(addUser.this, businessActivity.class));
            }
        });
    }
}