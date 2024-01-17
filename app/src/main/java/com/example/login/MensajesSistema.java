package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.ModelosDeClases.Mensaje;
import com.example.login.ModelosDeClases.MensajesAdapter;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MensajesSistema extends AppCompatActivity {

    TextView mensajes;
    private MensajesAdapter adaptador;
    private RecyclerView recyclerMensaje;
    private ArrayList<Mensaje> listaMensajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajes_sistema);

        listaMensajes=new ArrayList<>();
        recyclerMensaje=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerMensaje.setLayoutManager(new LinearLayoutManager(this));

        HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
        loggin.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggin);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://147.83.7.205:80/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        GetMensajes getMensajesservice = retrofit.create(GetMensajes.class);

        Call<ArrayList<Mensaje>> call = getMensajesservice.getMensajes();
        call.enqueue(new Callback<ArrayList<Mensaje>>() {
            @Override
            public void onResponse(Call<ArrayList<Mensaje>> call, Response<ArrayList<Mensaje>> response) {
                if (response.isSuccessful()) {
                    listaMensajes=response.body();
                    adaptador=new MensajesAdapter(listaMensajes,MensajesSistema.this);
                    recyclerMensaje.setAdapter(adaptador);

                   /* StringBuilder mensajesStringBuilder=new StringBuilder();
                    for(Mensaje mensaje:listaMensajes){
                        mensajesStringBuilder.append(mensaje.getMensaje()).append("\n");
                    }
                    mensajes.setText(mensajesStringBuilder.toString());*/

                }
            }
            @Override
            public void onFailure(Call<ArrayList<Mensaje>> call, Throwable t) {
                Toast.makeText(MensajesSistema.this, "error", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void volverOnClick(View view){
        Intent intent =new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}