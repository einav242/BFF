package com.example.bff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private EditText email;
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
                name.setText("name:"+user.getUsername());
                businessName.setText(user.getBusinessID());
                email.setText(user.getEmail());
                id.setText(user.getBusinessID());
                phone.setText(user.getPhone());
                city.setText(user.getPhone());
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
                if (!TextUtils.isEmpty(name.getText())) {
                    updateName(name.getText().toString());
                }
            }
        });

    }


    private boolean updateName(String newName) {
        FirebaseDatabase.getInstance().getReference().child("Business").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Business user = dataSnapshot.getValue(Business.class);
                String email = user.getEmail();
                String name = user.getName();
                if (!name.equals(newName)) {
                    reference.child(email).child("FullName").setValue(name);
                } else {
                    Toast.makeText(editBusiness.this, "same name", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return false;
    }


}

