package com.example.bff.view;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


import androidx.appcompat.app.AppCompatActivity;

import com.example.bff.R;


public class saleView extends AppCompatActivity {
    private ImageButton post;
    private ImageButton view;
    private ImageButton history;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(saleView.this , businessActivityView.class));
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        post = findViewById(R.id.post);
        view = findViewById(R.id.view);
        history = findViewById(R.id.history_sale);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(saleView.this, postSalesView.class));

            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(saleView.this, viewSaleView.class));
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(saleView.this, historyView.class));
            }
        });
    }

}
