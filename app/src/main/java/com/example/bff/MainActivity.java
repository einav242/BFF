package com.example.bff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sing_in(View view)
    {
        setContentView(R.layout.animalwindow);
    }

    public void sing_up(View view)
    {
        setContentView(R.layout.activity_register);
    }

    public void forget_password(View view)
    {
        setContentView(R.layout.forget_password);
    }
}