package com.example.bff.controller;

import com.example.bff.entities.Client;
import com.example.bff.model.addUserModel;
import com.example.bff.view.addUserView;

import java.util.ArrayList;

public class addUserController {
    addUserView view;
    addUserModel model;

    public addUserController(addUserView view) {
        this.view = view;
        model = new addUserModel(this);
    }

    public void RunApdater(ArrayList<Client> lst) {
        model.getListModel(lst);
    }

    public void setListController(ArrayList<Client> lst){
        view.setListView(lst);
    }
}
