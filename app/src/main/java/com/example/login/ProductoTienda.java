package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.ModelosDeClases.Avatar;
import com.example.login.ModelosDeClases.Jugador;
import com.example.login.ModelosDeClases.ProductoVo;
import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductoTienda extends AppCompatActivity {
    TextView id;
    ImageView imagen;
    TextView nom;
    TextView precio;
    TextView descrip ;
    TextView estado ;
    private ProgressBar spinner;

    int type;
    int effect;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_tienda);
        nom = (TextView) findViewById(R.id.nombre);
        precio= (TextView) findViewById(R.id.precio);
        descrip = (TextView) findViewById(R.id.descrip);
        imagen = (ImageView) findViewById(R.id.imageView2);
        Bundle miBundle = this.getIntent().getExtras();
        if(miBundle!=null){

            String nomb = miBundle.getString("nombre");
            nom.setText(nomb);

            Integer pr = miBundle.getInt("precio");
            precio.setText("Precio: "+pr+" $");

            String des = miBundle.getString("description");
            descrip.setText(des);

            String im = miBundle.getString("imagen");
            Picasso.get().load(im).into(imagen);

            type = miBundle.getInt("efectType");
            
            effect = miBundle.getInt("efect");


        }

        HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
        loggin.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggin);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://147.83.7.205:80/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        String username = SessionManager.getLoggedUsername(this);

        GetJugador jugador = retrofit.create(GetJugador.class);
        Call<Jugador> call2 = jugador.getJugador(username);
        call2.enqueue(new Callback<Jugador>() {
            @Override
            public void onResponse(Call<Jugador> call, Response<Jugador> response) {
                if (response.isSuccessful()) {
                    Jugador jugador = response.body();
                    int eurillos = jugador.getEurillos();
                    TextView coin = findViewById(R.id.textView4);
                    coin.setText(String.valueOf(eurillos));

                    String avatarJugador = jugador.getAvatar();
                    TextView avatarNombre = findViewById(R.id.nombreAvatar);
                    SeekBar vida = findViewById(R.id.vida);
                    SeekBar daño = findViewById(R.id.daño);
                    SeekBar speed = findViewById(R.id.speed);

                    vida.setSecondaryProgress(0);
                    daño.setSecondaryProgress(0);
                    speed.setSecondaryProgress(0);
                    vida.setEnabled(false);
                    daño.setEnabled(false);
                    speed.setEnabled(false);

                    AvatarService avatar = retrofit.create(AvatarService.class);
                    Call<Avatar> call3 = avatar.getAvatar(username, avatarJugador);
                    call3.enqueue(new Callback<Avatar>() {
                        @Override
                        public void onResponse(Call<Avatar> call, Response<Avatar> response) {
                            if (response.isSuccessful()) {
                                Avatar a = response.body();

                                avatarNombre.setText(a.getNombre());
                                vida.setProgress(a.getHealth());
                                daño.setProgress(a.getDamg());
                                speed.setProgress(a.getSpeed());

                                if (type == 0){
                                    int v = a.getHealth() + effect;
                                    if (v > 10){
                                        vida.setProgressDrawable(getResources().getDrawable(R.drawable.seek_bar_error));
                                        vida.setThumb(getResources().getDrawable(R.drawable.seek_bar_thumb));
                                        vida.setSecondaryProgress(10);
                                    }else{
                                        vida.setSecondaryProgress(v);
                                    }
                                } else if (type == 1) {
                                    int d = a.getDamg() + effect;
                                    if (d > 10){
                                        daño.setProgressDrawable(getResources().getDrawable(R.drawable.seek_bar_error));
                                        daño.setThumb(getResources().getDrawable(R.drawable.seek_bar_thumb));
                                        daño.setSecondaryProgress(10);
                                    }else{
                                        daño.setSecondaryProgress(d);
                                    }
                                } else if (type == 2) {
                                    int s = a.getSpeed() + effect;
                                    if (s > 10){
                                        speed.setProgressDrawable(getResources().getDrawable(R.drawable.seek_bar_error));
                                        speed.setThumb(getResources().getDrawable(R.drawable.seek_bar_thumb));
                                        speed.setSecondaryProgress(10);
                                    }else{
                                        speed.setSecondaryProgress(s);
                                    }
                                } else if (type == 3) {
                                    avatarNombre.setVisibility(View.INVISIBLE);
                                    vida.setVisibility(View.INVISIBLE);
                                    daño.setVisibility(View.INVISIBLE);
                                    speed.setVisibility(View.INVISIBLE);
                                    TextView t = findViewById(R.id.textView11);
                                    t.setVisibility(View.INVISIBLE);
                                    t = findViewById(R.id.textView13);
                                    t.setVisibility(View.INVISIBLE);
                                    t = findViewById(R.id.textView14);
                                    t.setVisibility(View.INVISIBLE);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Avatar> call, Throwable t) {
                            Toast.makeText(ProductoTienda.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Jugador> call, Throwable t) {
                Toast.makeText(ProductoTienda.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void salirClick(View view){
        finish();

    }
    public void comprarClick(View view){

        TextView coin = findViewById(R.id.textView4);
        String coinText = coin.getText().toString();
        String precioTexto = precio.getText().toString();
        String precioNumerico = precioTexto.replaceAll("[^0-9]", "");
        if (Integer.parseInt(coinText) >= Integer.parseInt(precioNumerico)) {

            spinner = (ProgressBar) findViewById(R.id.progressBar2);

            HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
            loggin.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(loggin);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://147.83.7.205:80/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            ComprarService producto = retrofit.create(ComprarService.class);

            String username = SessionManager.getLoggedUsername(this);

            Call<Jugador> call = producto.comprarProducto(nom.getText().toString(), username);
            spinner.setVisibility(View.VISIBLE);
            call.enqueue(new Callback<Jugador>() {
                @Override
                public void onResponse(Call<Jugador> call, Response<Jugador> response) {
                    if (response.isSuccessful()) {
                        spinner.setVisibility(View.GONE);
                        Toast.makeText(ProductoTienda.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ProductoTienda.this, Tienda.class);
                        startActivity(intent);
                        finish();
                    } else {
                        String valor = null;
                        if (type == 0){
                            valor = "de la vida máxima";
                        } else if (type == 1){
                            valor = "del daño máximo";
                        } else if (type == 2){
                            valor = "de la velocidad máxima";
                        }
                        Toast.makeText(ProductoTienda.this, "No puedes comprarlo porque pasarías " + valor, Toast.LENGTH_SHORT).show();
                        spinner.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<Jugador> call, Throwable t) {
                    Log.e("Retrofit", "Error: " + t.getMessage()); // Log the error message
                    Toast.makeText(ProductoTienda.this, "Error No response", Toast.LENGTH_SHORT).show();
                    spinner.setVisibility(View.GONE);

                }
            });
        } else{
            Toast.makeText(ProductoTienda.this, "Error, Estás tieso hermano", Toast.LENGTH_SHORT).show();
        }
    }
}