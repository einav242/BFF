package com.example.bff;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

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
                System.out.println("hi");
                startActivity(new Intent(MainActivity.this , forget_password.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText user_name = findViewById(R.id.editTextName);
                EditText password = findViewById(R.id.edtTxt2);
                if(MainActivity.this.permit=="empty")
                {
                    TextView txtFirstName = findViewById(R.id.textView4);
                    txtFirstName.setText("Please select a permission");
                    txtFirstName.setTextColor(Color.RED);
                }
                if (user_name.getText().toString().equals("") ||  password.getText().toString().equals("")) {
                    if (user_name.getText().toString().equals(""))
                    {
                        user_name.setError("Please Enter user name ");
                    }
                    if(password.getText().toString().equals(""))
                    {
                        password.setError("Please Enter password");
                    }
                    return;
                }
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
                else
                {
                    TextView txtFirstName = findViewById(R.id.textView4);
                   txtFirstName.setText("Please select a permission");
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
