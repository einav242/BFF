package com.example.bff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class search extends AppCompatActivity implements RecyclerViewInterface {
    RecyclerView recyclerView;
    ArrayList<Business> lst;
    DatabaseReference databaseReference;
    BusinessAdapter myadapt;
    private SearchView searchView;
    private SearchView searchCity;
    private ImageButton vet;
    private ImageButton hair;
    private ImageButton walker;
    private Button all;
    private String currentSearchText = "";
    private String currentSearchTextCity = "";
    private String selectedFilter = "all";
    public static ArrayList<Business> businessList = new ArrayList<Business>();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(search.this, animalActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView = findViewById(R.id.recyclebusiness);
        databaseReference = FirebaseDatabase.getInstance().getReference("Business");
        lst = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myadapt = new BusinessAdapter(this,lst,this);
        recyclerView.setAdapter(myadapt);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Business business = dataSnapshot.getValue(Business.class);
                    lst.add(business);
                }
                myadapt.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        searchView = findViewById(R.id.BusinessListSearchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s)
            {
                currentSearchText = s;
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

    @Override
    public void onItemClick(String email) {
        Intent intent = new Intent(search.this, seeMore.class);
        intent.putExtra("key",email);
        startActivity(intent);
    }

}


