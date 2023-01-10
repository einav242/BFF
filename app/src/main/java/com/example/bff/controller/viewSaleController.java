package com.example.bff.controller;

import com.example.bff.entities.sale;
import com.example.bff.model.viewSaleModel;
import com.example.bff.view.viewSaleView;

import java.util.ArrayList;

public class viewSaleController {
    viewSaleView view;
    viewSaleModel model;

    public viewSaleController(viewSaleView view) {
        this.view = view;
        model = new viewSaleModel(this);
    }

    public void SendControllerAdapter(ArrayList<sale> lst, String type) {
        model.sendModelAdapter(lst, type);
    }

    public void setListController(ArrayList<sale> lst) {
        view.setListView(lst);
    }

    public void removeSale(sale s) {
        model.removeSaleModel(s);
    }

    public void setScreenController() {
        view.setScreenView();
    }

    public void setTypeController(String type) {
        view.setTypeView(type);
    }

    public void getType() {
        model.findType();
    }
}
