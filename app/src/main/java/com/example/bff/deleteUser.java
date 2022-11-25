package com.example.bff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class deleteUser extends AppCompatActivity {
    EditText name, email, id;
    Button delete;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove_client);
        name = findViewById(R.id.editTextTextPersonName);
        email = findViewById(R.id.editTextTextEmailAddress);
        id = findViewById(R.id.editTextTextPersonName3);
        delete = findViewById(R.id.button5);
        DB = new DBHelper(this);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();

                Boolean checkdeletedata  = DB.deleteuserdata(nameTXT);
                if(checkdeletedata==true)
                {
                    Toast.makeText(deleteUser.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(deleteUser.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(deleteUser.this, businessActivity.class));
            }
        });
    }
}