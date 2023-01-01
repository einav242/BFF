package com.example.bff.view;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

import com.bumptech.glide.Glide;
import com.example.bff.entities.Business;
import com.example.bff.R;

import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.BusinessVh> {
    private final searchView search;
    Context context;
    ArrayList<Business> list;

    public BusinessAdapter(Context context, ArrayList<Business> list, searchView search) {
        this.search=search;
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public BusinessVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_business_list,parent,false);
        return new BusinessVh(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessVh holder, int position) {
        Business business = list.get(position);
        holder.Business_name.setText(business.getUsername());
        holder.type.setText(business.getType());
        holder.email=business.getEmail();
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(search != null)
                {
                    search.onItemClick(holder.email);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class BusinessVh extends RecyclerView.ViewHolder{
        TextView Business_name, type;
        String email;
        Button button;
        public BusinessVh(@NonNull View itemView){
            super(itemView);
            Business_name = itemView.findViewById(R.id.BusinessName);
            type = itemView.findViewById(R.id.type);
            button = itemView.findViewById(R.id.button10);
        }
    }
}