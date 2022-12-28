package com.example.bff.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bff.R;
import com.example.bff.controller.list_gotLostController;
import com.example.bff.entities.Business;
import com.example.bff.entities.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class list_gotLost_newView extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<User> lst;
    AnimalAdapter adapter;
    list_gotLostController controller;
    TextView empty;

    private SearchView searchColor;
    private SearchView searchBreed;
    private ImageButton cat;
    private ImageButton dog;
    private Button all;

    private String currentSearchTextColor = "";
    private String selectedFilter = "all";


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(list_gotLost_newView.this, animalActivityView.class));
        finish();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_got_lost_new);

        controller = new list_gotLostController(this);
        recyclerView = findViewById(R.id.recycleView);
        empty = findViewById(R.id.textViewgotLost);
        lst = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AnimalAdapter(this,lst,this);
        recyclerView.setAdapter(adapter);
        controller.addListController(lst);

        searchColor = findViewById(R.id.ColorSearch);
        searchColor.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<User> filteredUser = new ArrayList<User>();
                for(User user: lst) {
                    if (user.getColor().toLowerCase().contains(s.toLowerCase())) {
                        filteredUser.add(user);
                    }
                }
                setfilter(filteredUser);
                return false;
            }
        });
        searchBreed = findViewById(R.id.breedSearch);
        searchBreed.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                currentSearchTextColor = s;
                ArrayList<User> filteredUser = new ArrayList<User>();
                for(User user: lst) {
                    if (user.getBreed().toLowerCase().contains(s.toLowerCase())) {
                        if (selectedFilter.equals("all")) {
                            filteredUser.add(user);
                        } else {
                            if (user.getType().toLowerCase().equals(selectedFilter.toLowerCase()))  {
                                filteredUser.add(user);
                            }
                        }
                    }
                }
                setfilter(filteredUser);
                return false;
            }
        });

        cat = findViewById(R.id.imageButtonCat);
        dog = findViewById(R.id.imageButtonDog);
        all = findViewById(R.id.buttonAll);

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedFilter="all";
                ArrayList<User> filteredUser = new ArrayList<User>();
                for (User user : lst) {
                    filteredUser.add(user);
                }
                setfilter(filteredUser);
            }
        });

        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedFilter = "cat";
                ArrayList<User> filteredUser = new ArrayList<User>();
                if(currentSearchTextColor.isEmpty())
                {
                    for (User user : lst) {
                        if (user.getType().toLowerCase().equals("cat".toLowerCase())) {
                            filteredUser.add(user);
                        }
                    }
                    setfilter(filteredUser);
                }
            }
        });

       dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedFilter = "dog";
                ArrayList<User> filteredUser = new ArrayList<User>();
                for (User user : lst) {
                    if (user.getType().toLowerCase().equals("dog".toLowerCase())) {
                        filteredUser.add(user);
                    }
                }
                setfilter(filteredUser);
            }
        });

    }

    public void notifyView() {
        adapter.notifyDataSetChanged();
        if (lst == null){
            this.empty.setText("No Lost");
        }
    }


    public void setfilter(ArrayList<User> lst)
    {
        AnimalAdapter adpt = new AnimalAdapter(this,lst,this);
        recyclerView.setAdapter(adpt);
    }

    public void setlstView(ArrayList<User> lst) {
        this.lst = lst;
    }

}