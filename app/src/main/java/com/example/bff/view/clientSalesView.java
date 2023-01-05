package com.example.bff.view;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bff.R;
import com.example.bff.controller.clientSaleController;
import com.example.bff.entities.sale;

import java.util.ArrayList;


public class clientSalesView extends AppCompatActivity {
    clientSaleController controller;
    RecyclerView recyclerView;
    ArrayList<sale> lst;
    clientSaleAdapter myadapt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        recyclerView = findViewById(R.id.Recycleview);
        controller = new clientSaleController(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lst = new ArrayList<>();
        myadapt = new clientSaleAdapter(lst,this);
        recyclerView.setAdapter(myadapt);
        controller.SendControllerAdapter(lst);

    }
    public void appointment(String id, String name){
        Intent intent = new Intent(clientSalesView.this, make_appointmentView.class);
        intent.putExtra("key",id);
        intent.putExtra("name",name);
        startActivity(intent);
    }
    public void setListView(ArrayList<sale> lst) {
        this.lst = lst;
        myadapt.notifyDataSetChanged();
    }
}
