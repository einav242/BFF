package com.example.bff.view;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bff.R;
import com.example.bff.controller.ClientAdpterController;
import com.example.bff.entities.Client;

import java.util.ArrayList;

public class ClientAdapter extends RecyclerView.Adapter<ClientVH> {
    Context context;
    ArrayList<Client> lst;
    int flag;
    ClientAdpterController controller;
    addUserView addUser;
    viewClient viewclient;

    public ClientAdapter(ArrayList<Client> lst,Context context, int flag){
        this.lst=lst;
        this.context=context;
        this.flag = flag;
        controller=new ClientAdpterController(this);
        if(flag ==0){
            this.addUser = (addUserView)context;
        }
        else if(flag == 1){
            this.viewclient = (viewClient)context;
        }

    }
    @NonNull
    @Override
    public ClientVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(flag == 0){
            view = LayoutInflater.from(context)
                    .inflate(R.layout.clinetstry , parent, false);
        }
        else {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.view_client , parent, false);
        }

        return new ClientVH(view,flag).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientVH holder, int position) {
        Client c= lst.get(position);
        holder.c=c;
        holder.Email.setText(c.getEmail());
        holder.Phone.setText(c.getTime());
        holder.Date.setText(c.getDate());
        if(flag==0){
            holder.aprove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id="date: "+c.getDate().replace('/','-')+" hour: "+c.getTime().toString();
                    c.setStatus("approve");
                    controller.SendControlAprove(c,id);
                }
            });
            holder.decline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id="date: "+c.getDate().replace('/','-')+" hour: "+c.getTime();
                    c.setStatus("decline");
                    controller.SendControlDecline(c,id);
                }
            });
        }

        else if(flag==1){
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id="date: "+c.getDate().replace('/','-')+" hour: "+c.getTime();
                    controller.SendControlDelete(id,c);
                }
            });
        }
    }
    public void goneView(Client client){
        if(flag == 0){
            addUser.removeItem(client);
        }
        else if(flag == 1){
            viewclient.removeItem(client);
        }
    }

    @Override
    public int getItemCount() {
        return this.lst.size();
    }
}

    class ClientVH extends RecyclerView.ViewHolder{
        TextView Email , Phone, Date;
        Button aprove,decline, delete;
        Client c;
        private ClientAdapter adp;

        public ClientVH(@NonNull View itemView,int flag) {
            super(itemView);
            Email=itemView.findViewById(R.id.textviewname);
            Phone=itemView.findViewById(R.id.textviewPhone);
            Date=itemView.findViewById(R.id.dateviewPhone);
            if(flag==0){
                aprove=itemView.findViewById(R.id.aprove);
                decline=itemView.findViewById(R.id.decline);
            }
            else if(flag ==1){
                delete = itemView.findViewById(R.id.button11);
            }
        }
        public ClientVH linkAdapter(ClientAdapter adp){
            this.adp=adp;
            return this;
        }
}