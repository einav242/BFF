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

    public void SendControllerAdapter(ArrayList<sale> lst) {
        model.sendModelAdapter(lst);
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


}
