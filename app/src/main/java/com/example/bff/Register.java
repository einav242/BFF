//package com.example.bff;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.firestore.FirebaseFirestore;
//
//
//import java.util.HashMap;
//
//public class Register extends AppCompatActivity {
//
//    private EditText animalName;
//    private EditText name;
//    private EditText email;
//    private EditText password;
//    private EditText phone;
//    private ImageButton image;
//    private Button register;
//    private Button editImage;
//    private TextView loginUser;
//
//    private DatabaseReference mRootRef;
//    private FirebaseAuth mAuth;
//
//
//
//    ProgressDialog pd;
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//
//        animalName = findViewById(R.id.singUp_AnimalName);
//        name = findViewById(R.id.singUp_fullName);
//        email = findViewById(R.id.singUp_Email);
//        password = findViewById(R.id.singUp_Password);
//        phone = findViewById(R.id.editTextPhone);
//        register = findViewById(R.id.singUp_Register);
//        loginUser = findViewById(R.id.singUp_Button_LoginHere);
//
//        mRootRef = FirebaseDatabase.getInstance().getReference();
//        mAuth = FirebaseAuth.getInstance();
//        pd = new ProgressDialog(this);
//
//
//        // if to the user have already account
//        loginUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Register.this , MainActivity.class));
//            }
//        });
//
//
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String txtUsername = animalName.getText().toString();
//                String txtName = name.getText().toString();
//                String txtEmail = email.getText().toString();
//                String txtPassword = password.getText().toString();
//                String txtPhone = phone.getText().toString();
//
//                if (TextUtils.isEmpty(txtUsername) || TextUtils.isEmpty(txtName)
//                        || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword) || TextUtils.isEmpty(txtPhone)){
//                    Toast.makeText(Register.this, "Empty credentials!", Toast.LENGTH_SHORT).show();
//                } else if (txtPassword.length() < 6){
//                    Toast.makeText(Register.this, "Password too short!", Toast.LENGTH_SHORT).show();
//                } else {
//                    registerUser(txtUsername , txtName , txtEmail , txtPassword, txtPhone);
//                }
//            }
//        });
//    }
//
//    private void registerUser(final String username, final String name, final String email, String password, String phone) {
//
//        pd.setMessage("Please Wait!");
//        pd.show();
//
//        mAuth.createUserWithEmailAndPassword(email , password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//            @Override
//            public void onSuccess(AuthResult authResult) {
//
//                HashMap<String , Object> map = new HashMap<>();
//                map.put("name" , name);
//                map.put("email", email);
//                map.put("username" , username);
//                map.put("phone",phone);
//                map.put("flag","animal");
//                map.put("id" , mAuth.getCurrentUser().getUid());
//                mRootRef.child("Users").child(mAuth.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()){
//                            pd.dismiss();
//                            Toast.makeText(Register.this, "Update the profile " +
//                                    "for better expereince", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(Register.this , MainActivity.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            startActivity(intent);
//                            finish();
//                        }
//                    }
//                });
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                pd.dismiss();
//                Toast.makeText(Register.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
//}