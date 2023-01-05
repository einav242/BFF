package com.example.bff.controller;

import com.example.bff.entities.sale;
import com.example.bff.model.historyModel;
import com.example.bff.view.historyView;

import java.util.ArrayList;

public class historyController {
    historyView view;
    historyModel model;

    public historyController(historyView view) {
        this.view = view;
        model = new historyModel(this);
    }

    public void SendControllerAdapter(ArrayList<sale> lst) {
        model.sendModelAdapter(lst);
    }

    public void returnSale(sale s) {
        model.returnSaleModel(s);
    }

    public void setListController(ArrayList<sale> lst) {
        view.setListView(lst);
    }

    public void setScreenController() {
        view.setScreenView();
    }
}
