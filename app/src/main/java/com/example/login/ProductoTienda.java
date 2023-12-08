package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.ModelosDeClases.Jugador;
import com.example.login.ModelosDeClases.ProductoVo;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductoTienda extends AppCompatActivity {
    TextView id;
    TextView nom;
    TextView precio;
    TextView descrip ;
    TextView estado ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_tienda);
        id = (TextView) findViewById(R.id.idd);
        nom = (TextView) findViewById(R.id.nombre);
        precio= (TextView) findViewById(R.id.precio);
        descrip = (TextView) findViewById(R.id.descrip);
        estado = (TextView) findViewById(R.id.efectType);
        Bundle miBundle = this.getIntent().getExtras();
        if(miBundle!=null){
            Integer idd = miBundle.getInt("id");
            id.setText("Id: "+idd);

            String nomb = miBundle.getString("nombre");
            nom.setText(nomb);

            Integer pr = miBundle.getInt("precio");
            precio.setText("Precio: "+pr+" $");

            Integer stat = miBundle.getInt("efect");
            estado.setText("Estado: "+stat);

            String des = miBundle.getString("decrip");
            descrip.setText(des);
        }
    }
    public void salirClick(View view){
        finish();

    }
    public void comprarClick(View view){
        HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
        loggin.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggin);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        ComprarService producto = retrofit.create(ComprarService.class);
        Call<Jugador> call = producto.comprarProducto(nom.getText().toString(),LoginActivity.getUsername());
        call.enqueue(new Callback<Jugador>() {
            @Override
            public void onResponse(Call<Jugador> call, Response<Jugador> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ProductoTienda.this, "Comprado correctamente", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ProductoTienda.this, "No tienes suficientes eurillos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Jugador> call, Throwable t) {
                Toast.makeText(ProductoTienda.this, "Error No response", Toast.LENGTH_SHORT).show();
            }
        });
    }
}