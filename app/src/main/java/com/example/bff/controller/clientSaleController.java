package com.example.bff.controller;

import com.example.bff.entities.sale;
import com.example.bff.model.clientSaleModel;
import com.example.bff.view.clientSalesView;

import java.util.ArrayList;

public class clientSaleController {
    clientSalesView view;
    clientSaleModel model;

    public clientSaleController(clientSalesView view) {
        this.view = view;
        model = new clientSaleModel(this);
    }

    public void SendControllerAdapter(ArrayList<sale> lst) {
        model.sendModelAdapter(lst);
    }

    public void setListController(ArrayList<sale> lst) {
        view.setListView(lst);
    }
}
