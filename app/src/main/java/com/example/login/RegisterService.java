package com.example.login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterService {

    @POST("/jugadores/reister/")
    Call<List<CredencialesRegistro>> CreateCredencialesRegistro(@Body CredencialesRegistro credenciales);
}
