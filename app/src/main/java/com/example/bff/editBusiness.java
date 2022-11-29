package com.example.bff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class editBusiness extends AppCompatActivity {

    private EditText name;
    private EditText businessName;
    private TextView email;
    private EditText id;
    private EditText phone;
    private EditText city;
    private EditText street;
    private EditText house_number;
    private EditText type;
    private Button update;
    private FirebaseUser mAuth;


    DatabaseReference reference; //for the database that save already


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_business_activity);

        //user can change
        reference = FirebaseDatabase.getInstance().getReference("Business");
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        name = findViewById(R.id.register_BO_fullName);
        businessName = findViewById(R.id.register_BO_BusinessName);
        email = findViewById(R.id.register_BO_Email);
        id = findViewById(R.id.register_BO_ID);
        phone = findViewById(R.id.register_BO_PhoneNumber);
        city = findViewById(R.id.register_BO_City);
        street = findViewById(R.id.register_BO_Street);
        house_number = findViewById(R.id.register_BO_StreetNum);
        type = findViewById(R.id.register_BO_TypeOfBusiness);
        update = findViewById(R.id.register_BO_Register);
        FirebaseDatabase.getInstance().getReference().child("Business").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Business user = dataSnapshot.getValue(Business.class);
                name.setText(user.getName());
                businessName.setText(user.getUsername());
                email.setText(user.getEmail());
                id.setText(user.getBusinessID());
                phone.setText(user.getPhone());
                city.setText(user.getCity());
                street.setText(user.getStreet());
                house_number.setText(user.getHouseNumber());
                type.setText(user.getType());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(name.getText().toString(),businessName.getText().toString(),id.getText().toString(),phone.getText().toString(),city.getText().toString(),street.getText().toString(),
                        house_number.getText().toString(),type.getText().toString());
                startActivity(new Intent(editBusiness.this, businessActivity.class));
            }
        });

    }


    private boolean update(String newName,String newBusinessName, String newId, String newPhone, String newCity, String newStreet, String newHouseNumber, String newType) {
        FirebaseDatabase.getInstance().getReference().child("Business").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Business user = dataSnapshot.getValue(Business.class);
                String name = user.getName();
                String businessName = user.getUsername();
                String ID = user.getId();
                String phone = user.getPhone();
                String city = user.getCity();
                String street = user.getStreet();
                String houseNumber = user.getHouseNumber();
                String type = user.getType();
                if (!name.equals(newName)) {
                    reference.child(mAuth.getUid()).child("name").setValue(newName);
                }
                if (!businessName.equals(newBusinessName)) {
                    reference.child(mAuth.getUid()).child("username").setValue(newBusinessName);
                }
                if (!ID.equals(newId)) {
                    reference.child(mAuth.getUid()).child("id").setValue(newId);
                }
                if (!phone.equals(newPhone)) {
                    reference.child(mAuth.getUid()).child("phone").setValue(newPhone);
                }
                if (!city.equals(newCity)) {
                    reference.child(mAuth.getUid()).child("city").setValue(newCity);
                }
                if (!street.equals(newStreet)) {
                    reference.child(mAuth.getUid()).child("street").setValue(newStreet);
                }
                if (!houseNumber.equals(newHouseNumber)) {
                    reference.child(mAuth.getUid()).child("houseNumber").setValue(newHouseNumber);
                }
                if (!type.equals(newType)) {
                    reference.child(mAuth.getUid()).child("type").setValue(newType);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return false;
    }


}

