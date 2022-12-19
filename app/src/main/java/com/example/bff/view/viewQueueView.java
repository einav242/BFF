package com.example.bff.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.bff.R;
import com.example.bff.controller.viewQueueController;
import com.example.bff.entities.queue;

import java.util.ArrayList;
import java.util.HashMap;

public class viewQueueView extends AppCompatActivity{

    RecyclerView recyclerView;
    ArrayList<queue> lst;
    queueAdapter myadapt;
    HashMap<String,String> names;
    viewQueueController controller;
    TextView empty;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(viewQueueView.this, animalActivityView.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        controller = new viewQueueController(this);
        controller.getBusinessNameController();
        recyclerView = findViewById(R.id.Recycleview);
        empty = findViewById(R.id.textView41);
        lst = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myadapt = new queueAdapter(lst,this);
        recyclerView.setAdapter(myadapt);
        controller.getListController(names,lst);
    }

    public void notifyView(ArrayList<queue> lst){
        this.lst = lst;
        myadapt.notifyDataSetChanged();
        if(lst == null){
            empty.setText("No appointment");
        }
    }

    public void setBusinessNameView(HashMap<String, String> names){
        this.names = names;
    }
}


