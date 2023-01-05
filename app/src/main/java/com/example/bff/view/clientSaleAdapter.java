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
import com.example.bff.entities.sale;

import java.util.ArrayList;

public class clientSaleAdapter extends RecyclerView.Adapter<clientSaleVH> {
    Context context;
    ArrayList<sale> lst;
    private final clientSalesView viewSale;

    public clientSaleAdapter(ArrayList<sale> lst,Context context){
        this.lst=lst;
        this.context=context;
        this.viewSale = (clientSalesView) context;
    }
    @NonNull
    @Override
    public clientSaleVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.activity_client_sale, parent, false);;
        return new clientSaleVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull clientSaleVH holder, int position) {
        sale s = lst.get(position);
        holder.s =s;
        holder.name.setText(s.getBusinessName());
        holder.phone.setText(s.getBusinessPhone());
        holder.animal.setText(s.getAnimal());
        holder.description.setText(s.getDescription());
        holder.appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewSale.appointment(holder.s.getBusinessId(),holder.s.getBusinessName());
            }
        });

    }


    @Override
    public int getItemCount() {
        return this.lst.size();
    }
}

class clientSaleVH extends RecyclerView.ViewHolder{
    TextView animal, description,name, phone;
    Button appointment;
    sale s;
    private clientSaleAdapter adp;

    public clientSaleVH(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.BusinessName);
        phone = itemView.findViewById(R.id.phone1);
        animal = itemView.findViewById(R.id.f);
        description = itemView.findViewById(R.id.Description);
        appointment = itemView.findViewById(R.id.button11);
    }
    public clientSaleVH linkAdapter(clientSaleAdapter adp){
        this.adp=adp;
        return this;
    }
}