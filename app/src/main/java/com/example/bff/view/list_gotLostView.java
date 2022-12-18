package com.example.bff.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bff.R;
import com.example.bff.controller.list_gotLostController;
import com.example.bff.entities.User;
import com.example.bff.entities.queue;

import java.util.ArrayList;
import java.util.HashMap;

public class list_gotLostView extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<User> lst;
    AnimalAdapter adapter;
    list_gotLostController controller;
//    HashMap<String,String> names;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(list_gotLostView.this, animalActivityView.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_got_lost);

        controller = new list_gotLostController(this);
//        controller.getUserNameController();
        recyclerView = findViewById(R.id.recycleview);
        lst = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AnimalAdapter(this,lst);
        recyclerView.setAdapter(adapter);
//        controller.addListController(names,lst);

        controller.addListController(lst);
    }

    public void notifyView() {
        adapter.notifyDataSetChanged();
    }

//    public void setUserNameView(HashMap<String, String> names) {
//        this.names = names;
//    }
//    public void setListView(ArrayList<User> lst){
//        this.lst = lst;
//    }

}
