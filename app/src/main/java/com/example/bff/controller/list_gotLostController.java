package com.example.bff.controller;

import com.example.bff.entities.User;
import com.example.bff.entities.queue;
import com.example.bff.model.list_gotLostModel;
import com.example.bff.model.viewQueueModel;
import com.example.bff.view.list_gotLostView;

import java.util.ArrayList;
import java.util.HashMap;

public class list_gotLostController {
    list_gotLostView view;
    list_gotLostModel model;

    public list_gotLostController(list_gotLostView view) {
        this.view = view;
        model = new list_gotLostModel(this);
    }

//    public void addListController(HashMap<String, String> names,ArrayList<User> lst) {
//        model.allListModel(names,lst);
//    }

    public void addListController(ArrayList<User> lst) {
        model.allListModel(lst);
    }


    public void notifyDataSetChanged() {
        view.notifyView();
    }

//    public void getUserNameController() {
//        this.model.getUserNameModel();
//    }
//
//    public void setUserNameController(HashMap<String, String> names) {
//        view.setUserNameView(names);
//    }
//
//    public void setListController(ArrayList<User> lst){
//        view.setListView(lst);
//    }
}
