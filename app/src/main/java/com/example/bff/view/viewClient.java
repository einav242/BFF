package com.example.bff.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.bff.R;
import com.example.bff.controller.ViewClientController;
import com.example.bff.entities.Client;

import java.util.ArrayList;

public class viewClient extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Client> lst;
    ClientAdapter myadapt;
    ViewClientController controller;
    TextView empty;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(viewClient.this , businessActivityView.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        recyclerView = findViewById(R.id.Recycleview);
        empty = findViewById(R.id.textView41);
        controller=new ViewClientController(this);
        lst=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myadapt = new ClientAdapter(lst,this,1);
        recyclerView.setAdapter(myadapt);
        controller.SendControllerAdapter(lst);
    }

    public void setList(ArrayList<Client> lst){
        this.lst = lst;
        myadapt.notifyDataSetChanged();
        if(lst == null) {
            empty.setText("No Client");
        }
    }

    public void removeItem(){
        Intent intent = new Intent(viewClient.this, viewClient.class);
        startActivity(intent);
        finish();
    }
}