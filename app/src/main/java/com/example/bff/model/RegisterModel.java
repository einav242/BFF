//package com.example.bff.model;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//
//import com.example.bff.MainActivity;
//import com.example.bff.Register;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//
//import java.util.HashMap;
//
//public class RegisterModel {
//    private Context context;
//    ProgressDialog pd;
//
//
//    public RegisterModel(Context context) {
//        this.context = context;
//    }
//
//    public void registerUser(String txtUsername, String txtName, String txtEmail, String txtPassword, String txtPhone) {
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
//    }
//}
