package com.example.login;

import com.example.login.ModelosDeClases.Credenciales;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginService {

    @Headers("Content-Type:application/json")
    @POST("dsaApp/jugadores/login")
    Call<String> Createcredenciales(@Body Credenciales credenciales);
}
