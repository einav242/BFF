package com.example.bff.view;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bff.R;
import com.example.bff.entities.queue;

import java.util.ArrayList;

public class queueAdapter extends RecyclerView.Adapter<queueVH> {
    Context context;
    ArrayList<queue> lst;

    public queueAdapter(ArrayList<queue> lst,Context context){
        this.lst=lst;
        this.context=context;
    }
    @NonNull
    @Override
    public  queueVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.activity_appoiment_list, parent, false);
        return new queueVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  queueVH holder, int position) {
        queue q= lst.get(position);
        holder.name.setText(q.getBusinessName());
        holder.date.setText(q.getDate());
        holder.time.setText(q.getTime());
        holder.status.setText(q.getStatus());
    }

    @Override
    public int getItemCount() {
        return this.lst.size();
    }
}

class queueVH extends RecyclerView.ViewHolder{
    TextView name, date, time, status;
    private queueAdapter adp;

    public queueVH(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.BusinessName);
        date = itemView.findViewById(R.id.Date);
        time = itemView.findViewById(R.id.time);
        status = itemView.findViewById(R.id.status);
    }

}