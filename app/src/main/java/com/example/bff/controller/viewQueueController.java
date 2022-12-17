package com.example.bff.controller;

import com.example.bff.entities.queue;
import com.example.bff.model.viewQueueModel;
import com.example.bff.view.viewQueueView;

import java.util.ArrayList;
import java.util.HashMap;

public class viewQueueController {
    viewQueueModel model;
    viewQueueView view;

    public viewQueueController(viewQueueView view) {
        this.view = view;
        model = new viewQueueModel(this);
    }

    public void getListController(HashMap<String, String> names, ArrayList<queue> lst) {
        model.getListModel(names, lst);
    }
    public void setListController(ArrayList<queue> lst){
        view.setListView(lst);
    }
    public void notifyController(){
        view.notifyView();
    }
}
