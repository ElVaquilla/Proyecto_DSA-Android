package com.example.proyecto;

import com.example.proyecto.ModelosDeClases.ProductoVo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TiendaService {
    @GET("dsaApp/tienda/todos")
    Call<ArrayList<ProductoVo>>getTiendaProductos();

}
