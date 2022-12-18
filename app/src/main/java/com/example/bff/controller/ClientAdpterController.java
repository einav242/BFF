package com.example.bff.controller;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    public void SendControlAprove(Client c, String id, Button aprove, Button decline, View view) {
        model.SendModelAprove(c,id,aprove,decline,view);
    }

    public void SendControlDecline(Client c, String id, Button aprove, Button decline, View view) {
        model.SendModelDecline(c,id,aprove,decline,view);
    }

    public void SendControlDelete(String id, Button delete, View view) {
        model.SendModelDelete(id,delete,view);
    }
}
