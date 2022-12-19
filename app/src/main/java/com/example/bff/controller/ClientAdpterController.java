package com.example.bff.controller;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bff.entities.Client;
import com.example.bff.model.ClienyAdpterModel;
import com.example.bff.view.ClientAdapter;


public class ClientAdpterController {
    ClienyAdpterModel model;
    ClientAdapter view;

    public ClientAdpterController(ClientAdapter view) {
        this.view = view;
        model=new ClienyAdpterModel(this);
    }

    public void SendControlAprove(Client c, String id) {
        model.SendModelAprove(c,id);
    }

    public void SendControlDecline(Client c, String id) {
        model.SendModelDecline(c,id);
    }

    public void SendControlDelete(String id,Client client) {
        model.SendModelDelete(id,client);
    }
    public void goneViewController(Client client){
        this.view.goneView(client);
    }
}
