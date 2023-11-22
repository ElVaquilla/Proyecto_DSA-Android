package com.example.login;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TiendaService {

    @GET("tienda/todos")
    Call<ArrayList<ProductoVo>>getTiendaProductos();

}
