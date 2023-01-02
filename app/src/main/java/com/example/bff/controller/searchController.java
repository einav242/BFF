package com.example.bff.controller;

import com.example.bff.entities.Business;
import com.example.bff.entities.User;
import com.example.bff.view.BusinessAdapter;
import com.example.bff.model.searchModel;
import com.example.bff.view.searchView;

import java.util.ArrayList;

public class searchController {
    searchView view;
    searchModel model;

    public searchController(searchView view) {
        this.view = view;
        model = new searchModel(this);
    }
    public void addValueController(BusinessAdapter myadapt){
        model.addValueModel(myadapt);
    }


    public void setlstController(ArrayList<Business> lst){
        view.setlstView(lst);
    }

}
