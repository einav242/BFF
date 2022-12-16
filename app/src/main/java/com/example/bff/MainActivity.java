package com.example.bff;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.example.bff.view.MainActivity_view;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(MainActivity.this, MainActivity_view.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        finish();
    }
}



