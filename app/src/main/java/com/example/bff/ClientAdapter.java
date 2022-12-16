package com.example.bff;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

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
        holder.c=c;
        holder.Email.setText(c.getEmail());
        holder.Phone.setText(c.getTime());
        holder.Date.setText(c.getDate());
        holder.aprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id="date: "+c.getDate().replace('/','-')+" hour: "+c.getTime().toString();
                c.setStatus("aprove");
                FirebaseDatabase.getInstance().getReference().child("Em").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(id).setValue(c).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            holder.aprove.setVisibility(view.GONE);
                             holder.decline.setVisibility(view.GONE);
                        }
                        else {}

                    }
                });
            }
        });
        holder.decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id="date: "+c.getDate().replace('/','-')+" hour: "+c.getTime();
                c.setStatus("decline");
                FirebaseDatabase.getInstance().getReference().child("Em").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(id).setValue(c).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            holder.aprove.setVisibility(view.GONE);
                            holder.decline.setVisibility(view.GONE);

                        }
                        else {}

                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return this.lst.size();
    }
}

class ClientVH extends RecyclerView.ViewHolder{
    TextView Email , Phone, Date;
    Button aprove,decline;
    Client c;
    private ClientAdapter adp;

    public ClientVH(@NonNull View itemView) {
        super(itemView);
        Email=itemView.findViewById(R.id.textviewname);
        Phone=itemView.findViewById(R.id.textviewPhone);
        Date=itemView.findViewById(R.id.dateviewPhone);
        aprove=itemView.findViewById(R.id.aprove);
        decline=itemView.findViewById(R.id.decline);
//        aprove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String id="date: "+Date.toString().replace('-','/')+" hour: "+Phone.toString();
//                FirebaseDatabase.getInstance().getReference().child("Em").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(id).child("status").setValue("aprove").addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful()){
//                           aprove.setVisibility(view.GONE);
//                           decline.setVisibility(view.GONE);
//                        }
//                        else {}
//
//                    }
//                });
//            }
//        });
//        decline.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String id="date: "+Date.toString().replace('-','/')+" hour: "+Phone;
//                FirebaseDatabase.getInstance().getReference().child("Em").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(id).child("status").setValue("decline").addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful()){
//                            aprove.setVisibility(view.GONE);
//                            decline.setVisibility(view.GONE);
//                        }
//                        else {}
//
//                    }
//                });
//            }
//        });
    }
    public ClientVH linkAdapter(ClientAdapter adp){
        this.adp=adp;
        return this;
    }
}