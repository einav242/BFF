package com.example.bff.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.bff.R;
import com.example.bff.controller.historyController;
import com.example.bff.entities.sale;

import java.util.ArrayList;

public class historyView extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<sale> lst;
    saleAdapter myadapt;
    historyController controller;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(historyView.this , saleView.class));
        finish();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        recyclerView = findViewById(R.id.Recycleview);
        controller=new historyController(this);
        lst=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myadapt = new saleAdapter(lst,this,1);
        recyclerView.setAdapter(myadapt);
        controller.SendControllerAdapter(lst);
    }


    public void setScreenView(){
        Intent intent = new Intent(historyView.this, historyView.class);
        startActivity(intent);
        finish();
    }

    public void setListView(ArrayList<sale> lst) {
        this.lst = lst;
        myadapt.notifyDataSetChanged();
    }

    public void returnSale(sale s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(historyView.this);
        builder.setMessage("Do you want to return this sale ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            controller.returnSale(s);
        });
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}