package com.example.bff.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.bff.R;

public class WebViewActivityView extends AppCompatActivity {

    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.papiz.co.il/%D7%9B%D7%9C%D7%91%D7%99%D7%9D-%D7%95%D7%97%D7%AA%D7%95%D7%9C%D7%99%D7%9D/");

    }
}