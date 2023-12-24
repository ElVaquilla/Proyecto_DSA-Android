package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.login.R;

public class MainMenu extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        this.window = getWindow();
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

    }
    public void onClickMensajesSistema(View view){
        Intent intent =new Intent(this, MensajesSistema.class);
        startActivity(intent);
    }
}