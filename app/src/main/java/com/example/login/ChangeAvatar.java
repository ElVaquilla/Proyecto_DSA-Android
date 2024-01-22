package com.example.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.ModelosDeClases.Avatar;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChangeAvatar extends AppCompatActivity {

    int i;
    ArrayList<Avatar> listaAvatares;
    ImageView avatarImage;
    TextView avatarNombre;
    SeekBar vida;
    SeekBar daño;
    SeekBar speed;

    AvatarService avatares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);

        avatarImage =findViewById(R.id.avatarImage);
        avatarNombre = findViewById(R.id.nombreAvatar);
        vida = findViewById(R.id.vida);
        vida.setEnabled(false);
        daño = findViewById(R.id.daño);
        daño.setEnabled(false);
        speed = findViewById(R.id.speed);
        speed.setEnabled(false);


        String username = SessionManager.getLoggedUsername(this);

        HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
        loggin.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggin);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://147.83.7.205:80/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        avatares = retrofit.create(AvatarService.class);
        Call<ArrayList<Avatar>> call = avatares.getAvatares(username);
        call.enqueue(new Callback<ArrayList<Avatar>>() {
            @Override
            public void onResponse(Call<ArrayList<Avatar>> call, Response<ArrayList<Avatar>> response) {
                if (response.isSuccessful()){

                    listaAvatares = response.body();
                    i = 0;
                    setAvatar();

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Avatar>> call, Throwable t) {
                Toast.makeText(ChangeAvatar.this,"error",Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void setAvatar(){
        Avatar a = listaAvatares.get(i);
        String avatar = a.getNombre();
        avatarNombre.setText(avatar);

        ImageView image = findViewById(R.id.avatarImage);
        if (avatar.equals("Mario")){
            image.setBackground(getDrawable(R.drawable.mario));
        } else if (avatar.equals("Tom")){
            image.setBackground(getDrawable(R.drawable.tom));
        } else if (avatar.equals("Paco")){
            image.setBackground(getDrawable(R.drawable.paco));
        }

        vida.setProgress(a.getHealth());
        daño.setProgress(a.getDamg());
        speed.setProgress(a.getSpeed());
    }

    public void onClickNext(View view){
        if (i == 2){
            i = 0;
        }
        else{
            i++;
        }
        setAvatar();
    }

    public void onClickAtras(View view){
        Intent intent=new Intent(this, MainMenu.class);
        startActivity(intent);
        finish();
    }

    public void onClickSeleccionar(View view){
        ProgressBar spin = findViewById(R.id.spin);
        spin.setVisibility(View.VISIBLE);
        Avatar a = listaAvatares.get(i);
        Call<Void> call2 = avatares.updateAvatar(a);
        call2.enqueue(new Callback<Void>() {
            public void onResponse(Call<Void> call2, Response<Void> response) {
                if (response.isSuccessful()){
                    spin.setVisibility(View.INVISIBLE);
                    Intent intent=new Intent(ChangeAvatar.this, MainMenu.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    spin.setVisibility(View.INVISIBLE);
                    Toast.makeText(ChangeAvatar.this,"error",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call2, Throwable t) {
                spin.setVisibility(View.INVISIBLE);
                Toast.makeText(ChangeAvatar.this,"error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
