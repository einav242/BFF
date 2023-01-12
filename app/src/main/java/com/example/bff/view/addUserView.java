package com.example.bff.view;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.bff.R;
import com.example.bff.controller.addUserController;
import com.example.bff.entities.queue;

import java.util.ArrayList;

public class addUserView extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<queue> lst;
    ClientAdapter myadapt;
    addUserController controller;
    TextView empty;
    String id;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(addUserView.this , businessActivityView.class);
        intent.putExtra("key",id);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("key");
        }
        empty = findViewById(R.id.textView41);
        controller = new addUserController(this, this.id);
        recyclerView = findViewById(R.id.Recycleview);
        lst=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myadapt = new ClientAdapter(lst,this,0, this.id);
        recyclerView.setAdapter(myadapt);
        controller.RunApdater(lst);
    }

    public void setListView(ArrayList<queue> lst1) {
        this.lst = lst1;
        myadapt.notifyDataSetChanged();
        if(lst == null){
            empty.setText("No Client");
        }
    }

    public void removeItem(){
        Intent intent = new Intent(addUserView.this, addUserView.class);
        intent.putExtra("key",this.id);
        startActivity(intent);
        finish();
    }
}