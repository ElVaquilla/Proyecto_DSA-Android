package com.example.login.ModelosDeClases;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ChangeService {

    @Headers("Content-Type:application/json")
    @POST("dsaApp/jugadores/updatePassword/")
    Call<CredencialesRespuesta> CreateCredencialesChangePwd(@Body CredencialesChangePassword credenciales);
   @Headers("Content-Type:application/json")
    @POST("dsaApp/jugadores/updateUsername/")
    Call<CredencialesRespuesta>CreateCredencialesChangeUsername(@Body CredencialesChangeUsername credenciales);
}
