package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.ModelosDeClases.Jugador;
import com.example.login.R;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainMenu extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        this.window = getWindow();

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
                    TextView coin = findViewById(R.id.dineros);
                    coin.setText(String.valueOf(eurillos));
                    String avatar = jugador.getAvatar();

                    ImageView image = findViewById(R.id.avatar);
                    if (avatar.equals("Mario")){
                        image.setBackground(getDrawable(R.drawable.mario));
                    } else if (avatar.equals("Tom")){
                        image.setBackground(getDrawable(R.drawable.tom));
                    } else if (avatar.equals("Paco")){
                        image.setBackground(getDrawable(R.drawable.paco));
                    }
                }
            }

            @Override
            public void onFailure(Call<Jugador> call, Throwable t) {
                Toast.makeText(MainMenu.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClickChange(View view){
        Intent intent =new Intent(this, ChangeAvatar.class);
        startActivity(intent);
        finish();
    }

    public void onProfileImageClick(View view){
        Intent intent =new Intent(this, Profile.class);
        startActivity(intent);
        finish();
    }
    public void onStartGameClick(View view){

        Intent launchIntent=getPackageManager().getLaunchIntentForPackage("com.DefaultCompany.ProyectoDSAUnity");
        if(launchIntent!=null){
            startActivity(launchIntent);
        }else{
            Toast.makeText(MainMenu.this,"error",Toast.LENGTH_SHORT).show();
        }
    }
    public void onShopClick(View view){
        Intent intent =new Intent(this, Tienda.class);
        startActivity(intent);
        finish();
    }
    public void onSettingsClick(View view){
        Intent intent =new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
    public void onClickMensajesSistema(View view){
        Intent intent =new Intent(this, MensajesSistema.class);
        startActivity(intent);
    }

    public void onQuestionClick(View view){
        Intent intent =new Intent(this, AddQuestion.class);
        startActivity(intent);
    }
}