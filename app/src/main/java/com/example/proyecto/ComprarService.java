package com.example.proyecto;

import com.example.proyecto.ModelosDeClases.Jugador;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ComprarService {
    @GET("dsaApp/tienda/comprar/{pnombre}/{nombre}")
    Call<Jugador>comprarProducto(@Path("pnombre") String pnombre, @Path("nombre") String nombre);
}
