package com.example.login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("/jugadores/login/")
    Call<List<Credenciales>> Createcredenciales(@Body Credenciales credenciales);
}
