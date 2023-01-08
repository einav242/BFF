package com.example.bff.view;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bff.R;
import com.example.bff.controller.ClientAdapterController;
import com.example.bff.entities.queue;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ClientAdapter extends RecyclerView.Adapter<ClientVH> {
    Context context;
    ArrayList<queue> lst;
    int flag;
    ClientAdapterController controller;
    addUserView addUser;
    viewClient viewclient;

    public ClientAdapter(ArrayList<queue> lst,Context context, int flag){
        this.lst=lst;
        this.context=context;
        this.flag = flag;
        controller=new ClientAdapterController(this);
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
                    .inflate(R.layout.activity_add_client, parent, false);
        }
        else {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.activity_client_list, parent, false);
        }

        return new ClientVH(view,flag).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientVH holder, int position) {
        queue q= lst.get(position);
        holder.q=q;
        holder.Email.setText(q.getEmail());
        holder.Phone.setText(q.getTime());
        holder.Date.setText(q.getDate());
        Glide.with(context).load(lst.get(position).getUser_image()).placeholder(R.drawable.profile).into(holder.profile);
        if(flag==0){
            holder.aprove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id="date: "+q.getDate().replace('/','-')+" hour: "+q.getTime().toString();
                    q.setStatus("approve");
                    controller.SendController(q,id);
                }
            });
            holder.decline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id="date: "+q.getDate().replace('/','-')+" hour: "+q.getTime();
                    q.setStatus("decline");
                    controller.SendController(q,id);
                }
            });
        }

        else if(flag==1){
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id="date: "+q.getDate().replace('/','-')+" hour: "+q.getTime();
                    controller.SendControlDelete(q,id);
                }
            });
        }
    }
    public void goneView(){
        if(flag == 0){
            addUser.removeItem();
        }
        else if(flag == 1){
            viewclient.removeItem();
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
        queue q;
        CircleImageView profile;
        private ClientAdapter adp;

        public ClientVH(@NonNull View itemView,int flag) {
            super(itemView);
            profile = itemView.findViewById(R.id.imageView13);
            Email=itemView.findViewById(R.id.textviewname);
            Phone=itemView.findViewById(R.id.textviewPhone);
            Date=itemView.findViewById(R.id.dateviewPhone);
            if(flag==0){
                aprove=itemView.findViewById(R.id.aprove);
                decline=itemView.findViewById(R.id.decline);
            }
            else if(flag ==1){
                delete = itemView.findViewById(R.id.decline);
            }
        }
        public ClientVH linkAdapter(ClientAdapter adp){
            this.adp=adp;
            return this;
        }
}