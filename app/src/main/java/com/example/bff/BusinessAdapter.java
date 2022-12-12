package com.example.bff;


import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.BusinessVh> {
    Context context;
    ArrayList<Business> list;

    public BusinessAdapter(Context context, ArrayList<Business> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BusinessVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.business_search,parent,false);
        Button see=v.findViewById(R.id.button10);
        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return new BusinessVh(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessVh holder, int position) {
        Business business = list.get(position);
        holder.Business_name.setText(business.getUsername());
        holder.type.setText(business.getType());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class BusinessVh extends RecyclerView.ViewHolder{
        TextView Business_name, type;
        public BusinessVh(@NonNull View itemView){
            super(itemView);
            Business_name = itemView.findViewById(R.id.BusinessName);
            type = itemView.findViewById(R.id.type);
        }
    }
}