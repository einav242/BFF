package com.example.bff.controller;

import com.example.bff.model.salesModel;
import com.example.bff.view.salesView;

public class salesController {
    salesModel model;
    salesView view;

    public salesController(salesView view) {
        this.view = view;
        model = new salesModel(this);
    }
}
