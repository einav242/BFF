package com.example.bff.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bff.R;

import com.example.bff.entities.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.MyViewHolder> {
    Context context;
    ArrayList<User> list;
    private final list_gotLost_newView search;

    public Uri imageUri;

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
        Glide.with(context).load(list.get(position).getImage()).into(holder.profile);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView userName, phone , color , breed , type ;
        CircleImageView profile;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            profile = itemView.findViewById(R.id.c_ImageViewId);
            userName = itemView.findViewById(R.id.textName);
            phone = itemView.findViewById(R.id.textPhone);
            color = itemView.findViewById(R.id.textColor);
            breed = itemView.findViewById(R.id.textBreed);
            type = itemView.findViewById(R.id.textType);
        }
    }
}
