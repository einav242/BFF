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
import com.example.bff.entities.User;

import java.util.ArrayList;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.MyViewHolder> {
    Context context;
    ArrayList<User> list;
    private final list_gotLost_newView search;

    public AnimalAdapter(Context context, ArrayList<User> list , list_gotLost_newView search) {
        this.context = context;
        this.list = list;
        this.search = search;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_lost_list,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = list.get(position);
        holder.userName.setText(user.getUsername());
        holder.phone.setText(user.getPhone());
        holder.type.setText(user.getType());
        holder.breed.setText(user.getBreed());
        holder.color.setText(user.getColor());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView userName, phone , color , breed , type;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.textName);
            phone = itemView.findViewById(R.id.textPhone);
            color = itemView.findViewById(R.id.textColor);
            breed = itemView.findViewById(R.id.textBreed);
            type = itemView.findViewById(R.id.textType);
        }
    }
}
