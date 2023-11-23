package com.example.login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginService {

    @Headers("Content-Type:application/json")
    @POST("dsaApp/jugadores/login")
    Call<Credenciales> Createcredenciales(@Body Credenciales credenciales);
}
