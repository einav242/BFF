package com.example.bff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class editBusiness extends AppCompatActivity {

    EditText edFullName, edAnimalName, edEmail , edPassword ;

    String _FULLNAME, _ANIMALNAME, _EMAIL, _PASSWORD;

    DatabaseReference reference; //for the database that save already


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);

        //user can change
        reference = FirebaseDatabase.getInstance().getReference("user");

        edFullName = findViewById(R.id.edit_user_fullName);
        edAnimalName = findViewById(R.id.edit_user_AnimalName);
        edEmail = findViewById(R.id.edit_user_Email);
        edPassword = findViewById(R.id.edit_user_Password);


        viewInitializations();
    }

    void viewInitializations() {
        edFullName = findViewById(R.id.edit_user_fullName);
        edAnimalName = findViewById(R.id.edit_user_AnimalName);
        edEmail = findViewById(R.id.edit_user_Email);
        edPassword = findViewById(R.id.edit_user_Password);

        // To show back button in actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    //update data
    // you cant change the primary ket (Email)
    public void update(View view){

        if(isfullNameChanged() || isanimelNameChanged() || isPasswordChanged()){
            Toast.makeText(this,"Profile Update Successfully",Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(this,"Data is same and can not be updated",Toast.LENGTH_LONG).show();
    }

    private boolean isPasswordChanged() {
        if(!_PASSWORD.equals(edPassword.getText().toString())){
            reference.child(_EMAIL).child("password").setValue(edPassword.getText().toString());
            _PASSWORD = edPassword.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isanimelNameChanged() {
        if(!_ANIMALNAME.equals(edAnimalName.getText().toString())){
            // Check what the primary key is _Email , how animalName is in the data
            reference.child(_EMAIL).child("animalName").setValue(edAnimalName.getText().toString());
            _ANIMALNAME = edAnimalName.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isfullNameChanged() {
        if(!_FULLNAME.equals(edFullName.getText().toString())){
            // Check what the primary key is _Email , how animalName is in the data
            reference.child(_EMAIL).child("FullName").setValue(edFullName.getText().toString());
            _FULLNAME = edFullName.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

}
