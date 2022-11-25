package com.example.bff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Currency;

public class Userlist extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> name, email, age;
    DBHelper DB;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        DB = new DBHelper(this);
        name = new ArrayList<>();
        email = new ArrayList<>();
        age = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this, name, email, age);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
    }

    private void displaydata()
    {
        Cursor cursor = DB.getdata();
        if(cursor.getCount()==0)
        {
            Toast.makeText(Userlist.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            {
                name.add(cursor.getString(0));
                email.add(cursor.getString(1));
                age.add(cursor.getString(2));
            }
        }
    }
}