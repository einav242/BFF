package com.example.bff;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class deleteUser extends AppCompatActivity {
    EditText ID;
    Button delete;
    private DatabaseReference mRootRef;
    private FirebaseAuth mAuth;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove_client);
//        ID = findViewById(R.id.editTextTextEmailAddress);
//        delete = findViewById(R.id.button5);
//        pd = new ProgressDialog(this);
//        mRootRef = FirebaseDatabase.getInstance().getReference();
//        mAuth = FirebaseAuth.getInstance();
//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleter(ID.getText().toString());
//
//               }
//        });
    }

    private void deleter(String ID) {
        pd.setMessage("Please Wait!");
        pd.show();
        mRootRef = FirebaseDatabase.getInstance().getReference();
        FirebaseDatabase.getInstance().getReference().child("Em").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Em user = dataSnapshot.getValue(Em.class);
                if (user==null)
                {
                    Toast.makeText(deleteUser.this, "Data don't exist ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!user.getClients().containsKey(ID))
                {
                    Toast.makeText(deleteUser.this, "user don't exist ", Toast.LENGTH_SHORT).show();
                    return;
                }
                user.getClients().remove(ID);
                HashMap<String , Object> map = new HashMap<>();
                map.put("bussniessemail" , mAuth.getCurrentUser().getEmail());
                map.put("clients",user.getClients());
                mRootRef.child("Em").child(mAuth.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            pd.dismiss();
                            Toast.makeText(deleteUser.this, "Update the profile " +
                                    "for better expereince", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(deleteUser.this , businessActivity.class);
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