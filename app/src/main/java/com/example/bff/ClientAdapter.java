package com.example.bff;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClientAdapter extends RecyclerView.Adapter<ClientVH> {
    Context context;
    ArrayList<Client> lst;

    public ClientAdapter(ArrayList<Client> lst,Context context){
        this.lst=lst;
        this.context=context;
    }
    @NonNull
    @Override
    public ClientVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.clinetstry , parent, false);
        return new ClientVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientVH holder, int position) {
        Client c= lst.get(position);
        holder.Email.setText(c.getEmail());
        holder.Phone.setText(c.getTime());

    }

    @Override
    public int getItemCount() {
        return this.lst.size();
    }
}

class ClientVH extends RecyclerView.ViewHolder{
    TextView Email , Phone;
    private ClientAdapter adp;

    public ClientVH(@NonNull View itemView) {
        super(itemView);
        Email=itemView.findViewById(R.id.textviewname);
        Phone=itemView.findViewById(R.id.textviewPhone);
    }
    public ClientVH linkAdapter(ClientAdapter adp){
        this.adp=adp;
        return this;
    }
}