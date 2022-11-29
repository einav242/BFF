package com.example.bff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class addUser extends AppCompatActivity {
    EditText ID, email, phone, date;
    Button add;
    private DatabaseReference mRootRef;
    private FirebaseAuth mAuth;
    ProgressDialog pd;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_client);
        ID = findViewById(R.id.editTextTextPersonName);
        email = findViewById(R.id.editTextTextEmailAddress);
        phone = findViewById(R.id.editTextPhone);
        date = findViewById(R.id.editTextDate);
        add =findViewById(R.id.button5);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtID = ID.getText().toString();
                String txtEmail = email.getText().toString();
                String txtphone = phone.getText().toString();
                String txtDate = date.getText().toString();

                if (TextUtils.isEmpty(txtID)
                        || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtphone) || TextUtils.isEmpty(txtDate)){
                    Toast.makeText(addUser.this, "Empty credentials!", Toast.LENGTH_SHORT).show();
                } else {
                    addUsersD(txtID , txtEmail , txtphone,txtDate);
                }
            }
        });
    }
    private void addUsersD(final String ID, final String email, String phone,String date){
        pd.setMessage("Please Wait!");
        pd.show();
        mRootRef = FirebaseDatabase.getInstance().getReference();
        FirebaseDatabase.getInstance().getReference().child("Em").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Em user = dataSnapshot.getValue(Em.class);
                if (user==null)
                {
                    user=new Em();
                }
                Client client=new Client(phone,date,email);
                user.getClients().put(ID,client);
                HashMap<String , Object> map = new HashMap<>();
                map.put("bussniessemail" , mAuth.getCurrentUser().getEmail());
                map.put("clients",user.getClients());
                mRootRef.child("Em").child(mAuth.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            pd.dismiss();
                            Toast.makeText(addUser.this, "Update the profile " +
                                    "for better expereince", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(addUser.this , businessActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}