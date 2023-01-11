package com.example.bff.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.bff.entities.Business;
import com.example.bff.R;
import com.example.bff.controller.searchController;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class searchView extends AppCompatActivity{
    searchController controller;
    RecyclerView recyclerView;
    ArrayList<Business> lst;
    BusinessAdapter myadapt;
    private SearchView searchName;
    private SearchView searchCity;
    private ImageButton vet;
    private ImageButton hair;
    private ImageButton walker;
    private Button all;
    private String currentSearchTextCity = "";
    private String selectedFilter = "all";

    TextView empty;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(searchView.this, animalActivityView.class));
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        controller = new searchController(this);
        recyclerView = findViewById(R.id.recyclebusiness);
        lst = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myadapt = new BusinessAdapter(this,lst,this);
        empty = findViewById(R.id.textSearch);
        recyclerView.setAdapter(myadapt);


        controller.addValueController(myadapt);

        searchName = findViewById(R.id.BusinessListSearchView);
        searchName.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s)
            {
                ArrayList<Business> filteredBusiness = new ArrayList<Business>();
                for(Business business: lst) {
                    if (business.getUsername().toLowerCase().contains(s.toLowerCase())) {
                        filteredBusiness.add(business);
                    }
                }
                setfilter(filteredBusiness);
                return false;
            }
        });
        searchCity = findViewById(R.id.searchView);
        searchCity.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s)
            {
                currentSearchTextCity = s;
                ArrayList<Business> filteredBusiness = new ArrayList<Business>();
                for(Business business: lst) {
                    System.out.println(business.getUsername());
                    if (business.getCity().toLowerCase().contains(s.toLowerCase())) {
                        if (selectedFilter.equals("all")) {
                            filteredBusiness.add(business);
                        } else {
                            if (business.getType().toLowerCase().equals(selectedFilter.toLowerCase())) {
                                filteredBusiness.add(business);
                            }
                        }
                    }
                }
                setfilter(filteredBusiness);
                return false;
            }
        });
        vet = findViewById(R.id.imageButton12);
        hair = findViewById(R.id.imageButton);
        walker = findViewById(R.id.imageButton15);
        all = findViewById(R.id.button8);

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedFilter="all";
                ArrayList<Business> filteredBusiness = new ArrayList<Business>();
                for (Business business : lst) {
                    filteredBusiness.add(business);
                }
                setfilter(filteredBusiness);
            }
        });
        vet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedFilter = "veterinary medicine";
                ArrayList<Business> filteredBusiness = new ArrayList<Business>();
                if(currentSearchTextCity.isEmpty())
                {
                    for (Business business : lst) {
                        if (business.getType().toLowerCase().equals("veterinary medicine".toLowerCase())) {
                            filteredBusiness.add(business);
                        }
                    }
                    setfilter(filteredBusiness);
                }
            }
        });
        hair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedFilter = "hairdressing salon";
                ArrayList<Business> filteredBusiness = new ArrayList<Business>();
                for (Business business : lst) {
                    if (business.getType().toLowerCase().equals("hairdressing salon".toLowerCase())) {
                        filteredBusiness.add(business);
                    }
                }
                setfilter(filteredBusiness);
            }
        });
        walker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedFilter = "dog walker";
                ArrayList<Business> filteredBusiness = new ArrayList<Business>();
                for (Business business : lst) {
                    if (business.getType().toLowerCase().equals("dog walker".toLowerCase())) {
                        filteredBusiness.add(business);
                    }
                }
                setfilter(filteredBusiness);
            }
        });

    }
    public void setfilter(ArrayList<Business> lst)
    {
        BusinessAdapter adpt = new BusinessAdapter(this,lst,this);
        recyclerView.setAdapter(adpt);
    }

    public void onItemClick(String id) {
        Intent intent = new Intent(searchView.this, seeMoreView.class);
        intent.putExtra("key",id);
        startActivity(intent);
    }
    public void setlstView( ArrayList<Business> lst){
        this.lst = lst;
    }

}


