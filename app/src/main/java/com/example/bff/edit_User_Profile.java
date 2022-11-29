package com.example.bff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class edit_User_Profile extends AppCompatActivity {

    EditText edFullName;
    EditText edAnimalName;
    TextView edEmail;
    private Button update;
    private FirebaseUser mAuth;
    DatabaseReference reference; //for the database that save already


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);

        //user can change
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("User");
        edFullName = findViewById(R.id.edit_user_fullName);
        edAnimalName = findViewById(R.id.edit_user_AnimalName);
        edEmail = findViewById(R.id.edit_user_Email);
        update = findViewById(R.id.edit_user_Update);
        FirebaseDatabase.getInstance().getReference().child("User").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                edFullName.setText(user.getName());
                edAnimalName.setText(user.getUsername());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        update(edFullName.getText().toString(), edAnimalName.getText().toString());
        startActivity(new Intent(edit_User_Profile.this, animalActivity.class));
    }

    public void update(String newFullName, String newAnimalName)
    {
        FirebaseDatabase.getInstance().getReference().child("User").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                String fullName = user.getName();
                String animalName = user.getUsername();
                if (!fullName.equals(newFullName)) {
                    reference.child(mAuth.getUid()).child("name").setValue(newFullName);
                }
                if (!animalName.equals(newAnimalName)) {
                    reference.child(mAuth.getUid()).child("username").setValue(newAnimalName);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
