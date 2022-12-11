package com.example.bff;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BusinessAdapter extends RecyclerView.Adapter<BusinessVH> {
    Context context;
    ArrayList<Business> lst;

    public BusinessAdapter(ArrayList<Business> lst,Context context){
        this.lst=lst;
        this.context=context;
    }
    @NonNull
    @Override
    public BusinessVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.activity_search , parent, false);
        return new BusinessVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessVH holder, int position) {
        Business c= lst.get(position);
        holder.Email.setText(c.getEmail());
        holder.Phone.setText(c.getPhone());
    }

    @Override
    public int getItemCount() {
        return this.lst.size();
    }
}

class BusinessVH extends RecyclerView.ViewHolder{
    TextView Email , Phone;
    private BusinessAdapter adp;

    public BusinessVH(@NonNull View itemView) {
        super(itemView);
        Email=itemView.findViewById(R.id.textviewemail);
        Phone=itemView.findViewById(R.id.textviewType);
    }
    public BusinessVH linkAdapter(BusinessAdapter adp){
        this.adp=adp;
        return this;
    }
}