package com.example.bff.view;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bff.entities.Business;
import com.example.bff.R;
import com.example.bff.entities.RecyclerViewInterface;

import java.util.ArrayList;

public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.BusinessVh> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<Business> list;

    public BusinessAdapter(Context context, ArrayList<Business> list, RecyclerViewInterface recyclerViewInterface) {
        this.recyclerViewInterface=recyclerViewInterface;
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public BusinessVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.business_search,parent,false);
        return new BusinessVh(v,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessVh holder, int position) {
        Business business = list.get(position);
        holder.Business_name.setText(business.getUsername());
        holder.type.setText(business.getType());
        holder.email=business.getEmail();

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class BusinessVh extends RecyclerView.ViewHolder{
        TextView Business_name, type;
        String email;
        public BusinessVh(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface){
            super(itemView);
            Business_name = itemView.findViewById(R.id.BusinessName);
            type = itemView.findViewById(R.id.type);
            email="";
            Button button = itemView.findViewById(R.id.button10);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null)
                    {
                        recyclerViewInterface.onItemClick(email);
                    }
                }
            });
        }
    }
}