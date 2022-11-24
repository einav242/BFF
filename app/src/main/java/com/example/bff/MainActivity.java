package com.example.bff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button singupAnimal;
    private Button login;
    private Button singupBusiness;
    private Button forget;
    String permit="empty";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        singupAnimal = findViewById(R.id.button3);
        login = findViewById(R.id.button2);
        singupBusiness = findViewById(R.id.button4);
        forget = findViewById(R.id.button);


        singupAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , Register.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
        singupBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , register_BusinessOwner.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , forget_password.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(MainActivity.this.permit=="animal")
                {
                    startActivity(new Intent(MainActivity.this, animalActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    finish();
                }
                else if(MainActivity.this.permit=="business")
                {
                    startActivity(new Intent(MainActivity.this, businessActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    finish();
                }
            }
        });
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.animal:
                if (checked)
                    this.permit="animal";
                    break;
            case R.id.business:
                if (checked)
                    this.permit="business";
                    break;
        }
    }
}
