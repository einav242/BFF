package com.example.bff.controller;

import com.example.bff.entities.Client;
import com.example.bff.model.ViewClientModel;
import com.example.bff.view.ClientAdapter;
import com.example.bff.view.viewClient;

import java.util.ArrayList;

public class ViewClientController {
    viewClient view;
    ViewClientModel model;

    public ViewClientController(viewClient view) {
        this.view = view;
        model=new ViewClientModel(this);
    }

    public void SendControllAdpter(ClientAdapter myadapt, ArrayList<Client> lst) {
        model.SendModelAdpter(myadapt,lst);
    }
}
