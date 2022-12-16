//package com.example.bff;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Patterns;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.example.bff.R;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//
//public class forget_password extends AppCompatActivity {
//
//    EditText etEmail;
//    Button sendEmail;
//    private FirebaseAuth authProfile;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.forget_password);
//
//        getSupportActionBar().setTitle("Forgat Password");
//        etEmail = findViewById(R.id.et_email);
//        sendEmail = findViewById(R.id.bt_forget);
//        sendEmail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String email = etEmail.getText().toString();
//                if(TextUtils.isEmpty(email))
//                {
//                    Toast.makeText(forget_password.this,"Please enter your email",Toast.LENGTH_SHORT).show();
//                    etEmail.setError("Email is required");
//                    etEmail.requestFocus();
//                }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
//                {
//                    Toast.makeText(forget_password.this,"Please enter vaild email",Toast.LENGTH_SHORT).show();
//                    etEmail.setError("Valid email is required");
//                    etEmail.requestFocus();
//                }else{
//                    resetPassword(email);
//                }
//            }
//        });
//
//    }
//
//    private void resetPassword(String email) {
//        authProfile = FirebaseAuth.getInstance();
//        authProfile.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if(task.isSuccessful()){
//                    Toast.makeText(forget_password.this,"please cheke youre inbox for password resert link",Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(forget_password.this, MainActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
//                    Toast.makeText(forget_password.this,"Email send to Register Email Address",Toast.LENGTH_SHORT).show();
//                    finish();
//                }else{
//                    Toast.makeText(forget_password.this,"Something went worng",Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });
//    }
//
//}