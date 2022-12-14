package com.example.bff.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.bff.R;
import com.example.bff.controller.ViewClientController;

import com.example.bff.entities.queue;

import java.util.ArrayList;

public class viewClient extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<queue> lst;
    ClientAdapter myadapt;
    ViewClientController controller;
    TextView empty;
    String id;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(viewClient.this , businessActivityView.class);
        intent.putExtra("key",id);
        startActivity(intent);
        finish();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("key");
        }
        recyclerView = findViewById(R.id.Recycleview);
        empty = findViewById(R.id.textView41);
        controller=new ViewClientController(this,this.id);
        lst=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myadapt = new ClientAdapter(lst,this,1,this.id);
        recyclerView.setAdapter(myadapt);
        controller.SendControllerAdapter(lst);
    }

    public void setList(ArrayList<queue> lst){
        this.lst = lst;
        myadapt.notifyDataSetChanged();
        if(lst == null) {
            empty.setText("No Client");
        }
    }

    public void removeItem(){
        Intent intent = new Intent(viewClient.this, viewClient.class);
        intent.putExtra("key",this.id);
        startActivity(intent);
        finish();
    }
}