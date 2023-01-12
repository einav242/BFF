package com.example.bff.controller;

import com.example.bff.entities.User;
import com.example.bff.model.list_gotLostModel;
import com.example.bff.view.list_gotLost_newView;

import java.util.ArrayList;


public class list_gotLostController {
    list_gotLost_newView view;
//    list_gotLostView view;
    list_gotLostModel model;

//    public list_gotLostController(list_gotLostView view) {
//        this.view = view;
//        model = new list_gotLostModel(this);
//    }

    public list_gotLostController(list_gotLost_newView view) {
        this.view = view;
        model = new list_gotLostModel(this);
    }

    public void addListController(ArrayList<User> lst) {
        model.allListModel(lst);
    }


    public void notifyDataSetChanged() {
        view.notifyView();
    }


    public void setlstController(ArrayList<User> lst) {
        view.setlstView(lst);
    }

}
