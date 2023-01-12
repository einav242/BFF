package com.example.bff.controller;

import com.example.bff.entities.queue;
import com.example.bff.model.ClientAdapterModel;
import com.example.bff.view.ClientAdapter;


public class ClientAdapterController {
    ClientAdapterModel model;
    ClientAdapter view;

    public ClientAdapterController(ClientAdapter view, String id) {
        this.view = view;
        model=new ClientAdapterModel(this, id);
    }

    public void SendController(queue q, String id) {
        model.SendModel(q,id);
    }



    public void SendControlDelete(queue q,String id) {
        model.SendModelDelete(q,id);
    }
    public void goneViewController(){
        this.view.goneView();
    }
}
