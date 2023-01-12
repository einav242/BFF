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
    private String id;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(saleView.this , businessActivityView.class);
        intent.putExtra("key", id);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("key");
        }
        post = findViewById(R.id.post);
        view = findViewById(R.id.view);
        history = findViewById(R.id.history_sale);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(saleView.this, postSalesView.class);
                intent.putExtra("key", id);
                startActivity(intent);

            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(saleView.this, viewSaleView.class);
                intent.putExtra("key", id);
                startActivity(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(saleView.this, historyView.class);
                intent.putExtra("key", id);
                startActivity(intent);
            }
        });
    }

}
