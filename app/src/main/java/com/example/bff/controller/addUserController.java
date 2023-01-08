package com.example.bff.controller;

import com.example.bff.entities.queue;
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

    public void RunApdater(ArrayList<queue> lst) {
        model.getListModel(lst);
    }

    public void setListController(ArrayList<queue> lst){
        view.setListView(lst);
    }
}
