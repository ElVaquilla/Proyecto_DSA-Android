package com.example.login;

import com.example.login.ModelosDeClases.Partida;
import com.example.login.ModelosDeClases.ProductoVo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PartidaService {
    @GET("dsaApp/partidas/historialPartidas/{username}")
    Call<ArrayList<Partida>> getPartidas(@Path("username") String username);
}
