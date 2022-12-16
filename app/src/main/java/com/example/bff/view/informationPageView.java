package com.example.bff.view;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.bff.R;
import com.example.bff.WebViewActivity;

import java.util.HashMap;

public class informationPageView extends AppCompatActivity{

    private ImageButton homeButton;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.context = context;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_page);

        homeButton = findViewById(R.id.inf_imageButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, WebViewActivity.class);
                startActivity(intent);
            }
        });
    }

}
