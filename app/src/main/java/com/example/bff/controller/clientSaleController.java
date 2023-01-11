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

    public void SendControllerAdapter(ArrayList<sale> lst, String type) {
        model.sendModelAdapter(lst, type);
    }

    public void setListController(ArrayList<sale> lst) {
        view.setListView(lst);
    }

    public void setTypeController(String type) {
        view.setTypeView(type);
    }

    public void getType() {
        model.findTypeModel();
    }
}
