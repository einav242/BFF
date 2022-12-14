package com.example.bff.view;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bff.R;
import com.example.bff.controller.postSalesController;


public class postSalesView extends AppCompatActivity {
    private Button send;
    EditText description;
    postSalesController controller;
    String choice;
    ProgressDialog pd;
    String id;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(postSalesView.this , saleView.class);
        intent.putExtra("key",id);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_sales);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("key");
        }
        controller = new postSalesController(this,id);
        pd = new ProgressDialog(this);
        send = findViewById(R.id.button12);
        description = findViewById(R.id.editTextTextPersonName6);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.sendSalesController(description.getText().toString(),choice);
            }
        });
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

    public void setToastView(String msg){
        Toast.makeText(postSalesView.this,msg,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(postSalesView.this , saleView.class);
        intent.putExtra("key",id);
        startActivity(intent);
        finish();
    }
    public void setPd(){
        pd.dismiss();
    }
}
