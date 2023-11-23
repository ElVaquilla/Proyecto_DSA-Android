package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.login.ModelosDeClases.ProductoVo;

import java.util.ArrayList;
import retrofit2.Call;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Tienda extends AppCompatActivity {


    private ArrayList<ProductoVo> listProductos;
    private AdapterDatos adaptador;
    private RecyclerView recyclerProd;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);

        listProductos=new ArrayList<>();
        recyclerProd=(RecyclerView) findViewById(R.id.recyclerId);
        recyclerProd.setLayoutManager(new LinearLayoutManager(this));


    }
    public void menuClick(){
        startActivity(new Intent(Tienda.this, MainActivity.class));
    }
    //public void logonClick(View v) {
      //  startActivity(new Intent(MainActivity.this, LoginActivity.class));
    //}
    private void llenarProductManual(){
        listProductos.add(new ProductoVo("VIDA",8,"mkver",10,"jre",2));
        listProductos.add(new ProductoVo("VIDA",8,"mkver",10,"jre",2));
        listProductos.add(new ProductoVo("VIDA",8,"mkver",10,"jre",2));
        listProductos.add(new ProductoVo("VIDA",8,"mkver",10,"jre",2));
        listProductos.add(new ProductoVo("VIDA",8,"mkver",10,"jre",2));
        listProductos.add(new ProductoVo("VIDA",8,"mkver",10,"jre",2));
        listProductos.add(new ProductoVo("VIDA",8,"mkver",10,"jre",2));
        listProductos.add(new ProductoVo("VIDA",8,"mkver",10,"jre",2));




    }
    public void manualClick(View v) {
        llenarProductManual();
        AdapterDatos adapter=new AdapterDatos(listProductos,this);

        recyclerProd.setAdapter(adapter);
    }
    public void getClick(View v){

        HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
        loggin.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggin);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        TiendaService productos = retrofit.create(TiendaService.class);
        Call<ArrayList<ProductoVo>> call = productos.getTiendaProductos();
        call.enqueue(new Callback<ArrayList<ProductoVo>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductoVo>> call, Response<ArrayList<ProductoVo>> response) {
                if (response.isSuccessful()){
                    ArrayList<ProductoVo> listProductos = response.body();

                    AdapterDatos adapter=new AdapterDatos(listProductos,Tienda.this);

                    recyclerProd.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductoVo>> call, Throwable t) {
                Toast.makeText(Tienda.this,"error",Toast.LENGTH_SHORT).show();

            }
        });


    }


}