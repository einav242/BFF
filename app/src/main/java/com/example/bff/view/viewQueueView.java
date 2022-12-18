package com.example.bff.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(viewQueueView.this, animalActivityView.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.queue_list);

        controller = new viewQueueController(this);
        controller.getBusinessNameController();
        recyclerView = findViewById(R.id.Recycleview);
        lst = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myadapt = new queueAdapter(lst,this);
        recyclerView.setAdapter(myadapt);
        controller.getListController(names,lst);
    }

    public void setListView(ArrayList<queue> lst){
        this.lst = lst;
    }

    public void notifyView(){
        myadapt.notifyDataSetChanged();
    }

    public void setBusinessNameView(HashMap<String, String> names){
        this.names = names;
    }
}


