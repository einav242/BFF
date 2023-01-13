package com.example.bff.controller;

import com.example.bff.entities.queue;
import com.example.bff.model.viewQueueModel;
import com.example.bff.view.viewQueueView;

import java.util.ArrayList;
import java.util.HashMap;

public class viewQueueController {
    viewQueueModel model;
    viewQueueView view;

    public viewQueueController(viewQueueView view, String id) {
        this.view = view;
        model = new viewQueueModel(this,id);
    }

    public void getListController(HashMap<String, String> names, ArrayList<queue> lst) {
        model.getListModel(names, lst);
    }
    public void notifyController(ArrayList<queue> lst){
        view.notifyView(lst);
    }
    public void getBusinessNameController(){
        this.model.getBusinessNameModel();
    }
    public void setBusinessNameController(HashMap<String, String> names){
        view.setBusinessNameView(names);
    }
}
