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
import com.example.bff.controller.ClientAdapterController;
import com.example.bff.controller.viewSaleController;
import com.example.bff.entities.sale;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class saleAdapter extends RecyclerView.Adapter<SaleVH> {
    Context context;
    ArrayList<sale> lst;
    viewSaleView viewSale;
    historyView history;
    int flag;

    public saleAdapter(ArrayList<sale> lst,Context context,int flag){
        this.lst=lst;
        this.context=context;
        this.flag = flag;
        if (flag ==0){
            this.viewSale = (viewSaleView) context;
        }
        else{
            this.history = (historyView) context;
        }

    }
    @NonNull
    @Override
    public SaleVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (flag ==0){
            view = LayoutInflater.from(context)
                    .inflate(R.layout.activity_view_sale, parent, false);;
        }
        else {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.activity_history_sale, parent, false);;
        }
        return new SaleVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleVH holder, int position) {
        sale s = lst.get(position);
        holder.s =s;
        System.out.println(s.getAnimal());
        holder.animal.setText(s.getAnimal());
        holder.description.setText(s.getDescription());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag ==0){
                    viewSale.removeSale(holder.s);
                }
               else
                {
                    history.returnSale(holder.s);
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return this.lst.size();
    }
}

class SaleVH extends RecyclerView.ViewHolder{
    TextView animal, description;
    Button delete;
    sale s;
    private saleAdapter adp;


    public SaleVH(@NonNull View itemView) {
        super(itemView);
        animal = itemView.findViewById(R.id.f);
        description = itemView.findViewById(R.id.Description);
        delete = itemView.findViewById(R.id.button11);
    }
    public SaleVH linkAdapter(saleAdapter adp){
        this.adp=adp;
        return this;
    }
}