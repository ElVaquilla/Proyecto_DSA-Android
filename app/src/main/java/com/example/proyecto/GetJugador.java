package com.example.proyecto;

import com.example.proyecto.ModelosDeClases.Jugador;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetJugador {
    @GET("dsaApp/jugador/{nombre}")
    Call<Jugador> getJugador(@Path("nombre") String nombre);
}
