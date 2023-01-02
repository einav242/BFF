package com.example.bff.view;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bff.R;
import com.example.bff.controller.salesController;


public class salesView extends AppCompatActivity {
    private Button send;
    salesController controller;
    String choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        controller = new salesController(this);
    }
    public void onRadioButtonClicked (View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.animal:
                if (checked)
                    this.choice = "dog";
                break;
            case R.id.business:
                if (checked)
                    this.choice = "cat";
                break;
            case R.id.all:
                this.choice = "all";
                break;
        }
    }
}
