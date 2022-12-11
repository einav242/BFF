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
    Button add,btnview;
    EditText emailclin,phoneclin;
    String id;

    private DatabaseReference mRootRef;
    private FirebaseAuth mAuth;
    private DatabaseReference query;
    ProgressDialog pd;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_client);
        emailclin = findViewById(R.id.ClientnEmail);
        phoneclin = findViewById(R.id.ClientnPhone);
        add =findViewById(R.id.buttinset);
        btnview=findViewById(R.id.buttview);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtEmail = emailclin.getText().toString();
                String txtphone = phoneclin.getText().toString();

                if ( TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtphone) ){
                    Toast.makeText(addUser.this, "Empty credentials!", Toast.LENGTH_SHORT).show();
                } else {
                    addUsersD(txtEmail , txtphone);
                }
            }
        });
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(addUser.this, Clientlist.class));
                finish();
            }
        });
    }
    private void addUsersD( final String email, String phone){
        pd.show();
        id=mRootRef.push().getKey();

        Client client=new Client(phone,email);
        FirebaseDatabase.getInstance().getReference().child("Em").child(mAuth.getUid()).child(id).setValue(client).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    pd.dismiss();
                    Toast.makeText(addUser.this,"Client insert",Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}