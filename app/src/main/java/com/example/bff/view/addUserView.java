package com.example.bff.view;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.bff.R;
import com.example.bff.controller.addUserController;
import com.example.bff.entities.Client;

import java.util.ArrayList;

public class addUserView extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Client> lst;
    ClientAdapter myadapt;
    addUserController controller;
    TextView empty;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(addUserView.this , businessActivityView.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        empty = findViewById(R.id.textView41);
        controller = new addUserController(this);
        recyclerView = findViewById(R.id.Recycleview);
        lst=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myadapt = new ClientAdapter(lst,this,0);
        recyclerView.setAdapter(myadapt);
        controller.RunApdater(lst);
    }

    public void setListView(ArrayList<Client> lst1) {
        this.lst = lst1;
        myadapt.notifyDataSetChanged();
        if(lst == null){
            empty.setText("No Client");
        }
    }

    public void removeItem(){
        Intent intent = new Intent(addUserView.this, addUserView.class);
        startActivity(intent);
        finish();
    }
}