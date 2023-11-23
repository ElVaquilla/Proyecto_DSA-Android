package com.example.login;

import com.example.login.ModelosDeClases.Credenciales;
import com.example.login.ModelosDeClases.CredencialesRegistro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RegisterService {

    @Headers("Content-Type:application/json")
    @POST("dsaApp/jugadores/register/")
    Call<List<CredencialesRegistro>> CreateCredencialesRegistro(@Body CredencialesRegistro credenciales);
}
