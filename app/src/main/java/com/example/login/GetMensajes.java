package com.example.login;

import com.example.login.ModelosDeClases.Mensaje;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.http.GET;

public interface GetMensajes {
    @GET("dsaApp/mensajes/todosmen")
    Call<ArrayList<Mensaje>> getMensajes();
}