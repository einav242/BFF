package com.example.bff.controller;

import com.example.bff.entities.Client;
import com.example.bff.model.ClientAdapterModel;
import com.example.bff.view.ClientAdapter;


public class ClientAdapterController {
    ClientAdapterModel model;
    ClientAdapter view;

    public ClientAdapterController(ClientAdapter view) {
        this.view = view;
        model=new ClientAdapterModel(this);
    }

    public void SendControlAprove(Client c, String id) {
        model.SendModelAprove(c,id);
    }

    public void SendControlDecline(Client c, String id) {
        model.SendModelDecline(c,id);
    }

    public void SendControlDelete(String id) {
        model.SendModelDelete(id);
    }
    public void goneViewController(){
        this.view.goneView();
    }
}
