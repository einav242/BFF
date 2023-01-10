package com.example.bff.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.bff.R;
import com.example.bff.controller.ViewClientController;
import com.example.bff.controller.viewSaleController;
import com.example.bff.entities.sale;

import java.util.ArrayList;

public class viewSaleView extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<sale> lst;
    saleAdapter myadapt;
    viewSaleController controller;
    String type;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(viewSaleView.this , saleView.class));
        finish();
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        controller.getType();
        recyclerView = findViewById(R.id.Recycleview);
        controller=new viewSaleController(this);
        lst=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myadapt = new saleAdapter(lst,this,0);
        recyclerView.setAdapter(myadapt);
        controller.SendControllerAdapter(lst, type);
    }


    public void setScreenView(){
        Intent intent = new Intent(viewSaleView.this, viewSaleView.class);
        startActivity(intent);
        finish();
    }

    public void setListView(ArrayList<sale> lst) {
        this.lst = lst;
        myadapt.notifyDataSetChanged();
    }

    public void removeSale(sale s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(viewSaleView.this);
        builder.setMessage("Do you want to delete ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            controller.removeSale(s);
        });
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void setTypeView(String type) {
        this.type = type;
    }
}