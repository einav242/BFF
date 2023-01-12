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
    String id;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(viewSaleView.this , saleView.class);
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
        controller=new viewSaleController(this, this.id);
        lst=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myadapt = new saleAdapter(lst,this,0);
        recyclerView.setAdapter(myadapt);
        controller.SendControllerAdapter(lst);
    }


    public void setScreenView(){
        Intent intent = new Intent(viewSaleView.this, viewSaleView.class);
        intent.putExtra("key",id);
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


}