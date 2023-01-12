package com.example.bff.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

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
import com.example.bff.controller.animalActivityController;
import com.example.bff.entities.Notification;
import com.example.bff.entities.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class animalActivityView extends AppCompatActivity {

    private TextView title;
    private ImageButton information;
    private ImageButton edit;
    private ImageButton search;
    private ImageButton view;
    private ImageButton getlost;
    private ImageButton sale;
    private Button logOut;
    private animalActivityController controller;
    private String userName;
    private String id;

    public Uri imageUri;
    CircleImageView profilePic;
    private ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_window);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("key");
        }
        controller = new animalActivityController(this, id);
        getlost = findViewById(R.id.imageButton2);
        information = findViewById(R.id.imageButton10);
        edit = findViewById(R.id.imageButton8);
        title = findViewById(R.id.txtMessage);
        profilePic = findViewById(R.id.edit_user_image);
        search = findViewById(R.id.imageButton7);
        logOut = findViewById(R.id.singUp_LogOut);
        view = findViewById(R.id.imageButton13);
        sale = findViewById(R.id.imageButton4);

        pd = new ProgressDialog(this);
        controller.getUserNameController();
        controller.getImageProfile();
        ArrayList<Notification> lst=new ArrayList<>();
        controller.GetNotifications(lst);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("animal Chanel","animal Chanel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager= getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(animalActivityView.this, clientSalesView.class);
                intent.putExtra("key",id);
                startActivity(intent);

            }
        });


        getlost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(animalActivityView.this, getLostView.class);
                intent.putExtra("key",id);
                startActivity(intent);
            }
        });

        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(animalActivityView.this, informationPageView.class));
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(animalActivityView.this, EditUserProfileView.class);
                intent.putExtra("key",id);
                startActivity(intent);

            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(animalActivityView.this, searchView.class);
                intent.putExtra("key",id);
                startActivity(intent);

            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(animalActivityView.this);
                builder.setMessage("are you sure you want to exit ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    controller.logOut_controller();
                    Intent intent = new Intent(animalActivityView.this, MainActivityView.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(animalActivityView.this , "Logout Successful",Toast.LENGTH_SHORT).show();

                });
                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(animalActivityView.this, viewQueueView.class);
                intent.putExtra("key",id);
                startActivity(intent);
                finish();
            }
        });
    }


    public void setUserName(String name)
    {
        this.userName = name;
        title.setText("Hello "+this.userName);
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

    public void setImegeView(Uri uri) {
        profilePic.setImageURI(uri);
        imageUri = uri;
        Picasso.get().load(uri).into(profilePic);
    }

    public void setImage(User user) {
        Picasso.get().load(user.getImage()).placeholder(R.drawable.profile).into(profilePic);
    }

    public void SetNews(ArrayList<Notification> lst) {
        if (!lst.isEmpty()) {
            NotificationManagerCompat mange= NotificationManagerCompat.from(animalActivityView.this);
            int k=1;
            for (Notification s : lst) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(animalActivityView.this, "animal Chanel");
                builder.setContentTitle("New event");
                builder.setContentText(s.GetString());
                builder.setSmallIcon(R.drawable.ic_launcher_background);
                builder.setAutoCancel(true);
                mange.notify(k,builder.build());
                k++;
            }
            controller.setOld(lst);
        }


    }
}



