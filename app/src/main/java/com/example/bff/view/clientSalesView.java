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
    String type;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("key");
        }
        recyclerView = findViewById(R.id.Recycleview);
        controller = new clientSaleController(this, id);
        controller.getType();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lst = new ArrayList<>();
        myadapt = new clientSaleAdapter(lst,this);
        recyclerView.setAdapter(myadapt);
    }
    public void appointment(String id, String name, String image){
        Intent intent = new Intent(clientSalesView.this, seeMoreView.class);
        intent.putExtra("key",id);
        intent.putExtra("userID",this.id);
        startActivity(intent);
    }
    public void setListView(ArrayList<sale> lst) {
        this.lst = lst;
        myadapt.notifyDataSetChanged();
    }

    public void setTypeView(String type) {
        this.type = type;
        controller.SendControllerAdapter(lst, type);
    }
}
