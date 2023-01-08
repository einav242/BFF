package com.example.bff.controller;

import com.example.bff.entities.queue;
import com.example.bff.model.ViewClientModel;
import com.example.bff.view.viewClient;

import java.util.ArrayList;

public class ViewClientController {
    viewClient view;
    ViewClientModel model;

    public ViewClientController(viewClient view) {
        this.view = view;
        model=new ViewClientModel(this);
    }

    public void SendControllerAdapter(ArrayList<queue> lst) {
        model.SendModelAdpter(lst);
    }
    public void setListController(ArrayList<queue> lst){
        view.setList(lst);
    }
}
