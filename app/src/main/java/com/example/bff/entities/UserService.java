package com.example.bff.entities;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("login/")
    Call<loginResponse> loginUser(@Body loginRequest login);

    @POST("AccessLevel/")
    Call<String> checker(@Body AccessLevel level);

    @POST("RegisterUser/")
    Call<String> register(@Body RegisterUser user);
}

