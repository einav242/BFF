package com.example.bff.model;

import androidx.annotation.NonNull;

import com.example.bff.entities.AccessLevel;
import com.example.bff.entities.User;
import com.example.bff.controller.MainActivityController;
import com.example.bff.entities.loginRequest;
import com.example.bff.entities.loginResponse;
import com.example.bff.entities.serverAPI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.units.qual.A;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityModel {
    private MainActivityController controller;
    String permit;
    public MainActivityModel(MainActivityController controller) {
        this.controller = controller;
        permit = "empty";
    }

    public void login( String username, String  passwordE, String permit){
        this.permit = permit;
        loginRequest login = new loginRequest();
        login.setEmail(username);
        login.setPassword(passwordE);
        loginUser(login);
    }

    public void loginUser(loginRequest login){
        Call<loginResponse> loginResponseCall = serverAPI.getService().loginUser(login);
        loginResponseCall.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                if(response.isSuccessful()){
                    loginResponse res = response.body();
                    checkUserAccesLevel(res.getUser_id());
                }else{
                    controller.toast_controller("Log in Error on response");
                }
            }
            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                controller.toast_controller("Log in Error on failure");
            }
        });
    }


    private void checkUserAccesLevel(String uid) {
        String permit = this.permit;
        String type ="";
        if(permit=="animal") {
            type ="Users";
        }
        if(permit=="business"){
            type = "Business";
        }
        AccessLevel level = new AccessLevel();
        level.setType(type);
        level.setUid(uid);
        Call<String> booleanCall = serverAPI.getService().checker(level);
        booleanCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String res = response.body();
                if(response.isSuccessful()){
                    if(res.equals("true")){
                        controller.passActivity_controller(uid);
                    }
                    else {
                        controller.toast_controller("Log in Error: User Not Exist at that section");
                    }
                }else{
                    controller.toast_controller("Log in Error on response");
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                controller.toast_controller("Log in Error: AccessLevel");
            }
        });

    }

}
