package com.example.proyecto;

import com.example.proyecto.ModelosDeClases.CredencialesRegistro;
import com.example.proyecto.ModelosDeClases.CredencialesRespuesta;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RegisterService {

    @Headers("Content-Type:application/json")
    @POST("dsaApp/jugadores/register/")
    Call<CredencialesRespuesta> CreateCredencialesRegistro(@Body CredencialesRegistro credenciales);
}
