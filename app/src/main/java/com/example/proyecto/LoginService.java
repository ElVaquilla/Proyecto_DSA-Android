package com.example.proyecto;

import com.example.proyecto.ModelosDeClases.Credenciales;

import com.example.proyecto.ModelosDeClases.CredencialesRespuesta;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginService {

    @Headers("Content-Type:application/json")
    @POST("dsaApp/jugadores/login")
    Call<CredencialesRespuesta> Createcredenciales(@Body Credenciales credenciales);
}
