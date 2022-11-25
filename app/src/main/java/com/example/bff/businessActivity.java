package com.example.bff;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class businessActivity extends AppCompatActivity {
    EditText name, email, age;
    Button insert, view, delete;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_window);
    }

//        name = findViewById(R.id.name);
//        email= findViewById(R.id.email);
//        age = findViewById(R.id.age);
//        insert = findViewById(R.id.btnInsert);
//        view = findViewById(R.id.btnView);
//        delete = findViewById(R.id.btnDelete);
//
//        DB = new DBHelper(this);
//
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(businessActivity.this, Userlist.class));
//            }
//        });
//
//        insert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String nameTXT = name.getText().toString();
//                String emailTXT = email.getText().toString();
//                String ageTXT = age.getText().toString();
//
//                Boolean checkinsertdata  = DB.insertuserdata(nameTXT, emailTXT, ageTXT);
//                if(checkinsertdata==true)
//                {
//                    Toast.makeText(businessActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    Toast.makeText(businessActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//
//
//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String nameTXT = name.getText().toString();
//
//                Boolean checkdeletedata  = DB.deleteuserdata(nameTXT);
//                if(checkdeletedata==true)
//                {
//                    Toast.makeText(businessActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    Toast.makeText(businessActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//
//    }
}