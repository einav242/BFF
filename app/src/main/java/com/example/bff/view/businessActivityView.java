package com.example.bff.view;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bff.R;
import com.example.bff.controller.businessActivityController;

import com.example.bff.entities.Business;
import com.example.bff.entities.Notification;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class businessActivityView extends AppCompatActivity {
    private ImageButton view;
    private ImageButton insert;
    private ImageButton edit;
    private ImageButton sales;
    private TextView title;
    private businessActivityController controller;
    private Button logOut;


    private ProgressDialog pd;
    CircleImageView profilePic;
    public Uri imageUri;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_window);
        view = findViewById(R.id.imageButton5);
        insert = findViewById(R.id.imageButton3);
        edit = findViewById(R.id.imageButton8);
        title = findViewById(R.id.txtMessage);
        profilePic = findViewById(R.id.register_BO_image);
        logOut = findViewById(R.id.singUp_LogOut);
        sales = findViewById(R.id.imageButton6);
        pd = new ProgressDialog(this);
        controller = new businessActivityController(this);
        controller.getUserNameController();
        controller.getImageProfile();
        ArrayList<Notification> lst=new ArrayList<>();
        controller.GetNotfications(lst);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("Bussnies Chanel","Bussnies Chanel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager= getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(businessActivityView.this, viewClient.class));
                finish();
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(businessActivityView.this, addUserView.class));
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(businessActivityView.this, editBusinessView.class));
            }
        });
        sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(businessActivityView.this, saleView.class));
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(businessActivityView.this);
                builder.setMessage("are you sure you want to exit ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    controller.logout();
                    Intent intent = new Intent(businessActivityView.this, MainActivityView.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(businessActivityView.this , "Logout Successful",Toast.LENGTH_SHORT).show();
                });
                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    public void setUserName(String name){
        title.setText("Hello "+name);
    }
    public void setToastView(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    public void setPdView(String msg){
        pd.setTitle(msg);
        pd.show();
    }
    public void pdDismissView(){
        pd.dismiss();
    }

    public void picassoView(Uri uri){
        Picasso.get().load(uri).into(profilePic);
    }

    public void setImage(Business user) {
        Picasso.get().load(user.getImage()).placeholder(R.drawable.vetuser).into(profilePic);
    }

    public void SetNews(ArrayList<Notification> lst) {
        if (!lst.isEmpty()) {
            NotificationManagerCompat mange= NotificationManagerCompat.from(businessActivityView.this);
            int k=1;
            for (Notification s : lst) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(businessActivityView.this, "Bussnies Chanel");
                builder.setContentTitle("New event");
                builder.setContentText(s.GetString());
                builder.setSmallIcon(R.drawable.ic_launcher_background);
                builder.setAutoCancel(true);
                mange.notify(k,builder.build());
                k++;
            }
        }

    }

}
