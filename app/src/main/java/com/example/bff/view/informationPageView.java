package com.example.bff.view;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.bff.R;


public class informationPageView extends AppCompatActivity{

    private ImageButton homeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_page);

        homeButton = findViewById(R.id.inf_imageButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, WebViewActivityView.class);
                startActivity(intent);
            }
        });
    }

}
