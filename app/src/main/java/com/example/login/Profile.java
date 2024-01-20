package com.example.login;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.ModelosDeClases.Partida;
import com.example.login.ModelosDeClases.ProductoVo;
import com.example.login.R;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Profile extends AppCompatActivity {

    private ActivityResultLauncher<Intent>galleryLauncher;
    private ImageButton imageProfileButton;
    private SharedPreferences sharedPreferences;
    private Window window;
    private ArrayList<Partida> listaPartidas;
    private AdapterHistorial adaptador;
    private RecyclerView recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        imageProfileButton=findViewById(R.id.imageButtonProfile);
        sharedPreferences=getSharedPreferences("MyPrefs",MODE_PRIVATE);

        String savedImageUri=sharedPreferences.getString("profileimuri",null);
        if(savedImageUri!=null){
            imageProfileButton.setImageURI(Uri.parse(savedImageUri));
        }
        galleryLauncher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode()==RESULT_OK&&result.getData()!=null){
                        Uri selecteImUri=result.getData().getData();
                        updateProfileImage(selecteImUri);
                    }
                }
        );

        String username = SessionManager.getLoggedUsername(this);
        TextView nombre = findViewById(R.id.user);
        nombre.setText(String.valueOf(username));

        listaPartidas=new ArrayList<>();
        recycler=(RecyclerView) findViewById(R.id.recyclerView2);
        recycler.setLayoutManager(new LinearLayoutManager(this));


        HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
        loggin.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggin);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://147.83.7.205:80/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        PartidaService partidas = retrofit.create(PartidaService.class);
        Call<ArrayList<Partida>> call = partidas.getPartidas(username);
        call.enqueue(new Callback<ArrayList<Partida>>() {
            @Override
            public void onResponse(Call<ArrayList<Partida>> call, Response<ArrayList<Partida>> response) {
                if (response.isSuccessful()){
                    ArrayList<Partida> listaPartidas = response.body();

                    TextView t = findViewById(R.id.his);

                    if (listaPartidas.isEmpty()){
                        t.setVisibility(View.VISIBLE);
                    }

                    else{
                        t.setVisibility(View.GONE);

                        AdapterHistorial adapter=new AdapterHistorial(listaPartidas,Profile.this);
                        recycler.setAdapter(adapter);

                    }

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Partida>> call, Throwable t) {
                Toast.makeText(Profile.this,"error",Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void onChangeImageClick(View view){
        openGallery();
    }
    private void updateProfileImage(Uri newImageUri){
        imageProfileButton.setImageURI(newImageUri);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("profileimuri",newImageUri.toString());
        editor.apply();
    }
    public void onChangeUser(View view){
        String a ="changeUsername";
        Intent intent=new Intent(this,Change.class);
        intent.putExtra("ACTION",a);
        startActivity(intent);
    }
    private void openGallery(){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryLauncher.launch(galleryIntent);
    }
    public void onChangePassword(View view){
        String a = "changePassword";
        Intent intent=new Intent(this,Change.class);
        intent.putExtra("ACTION",a);
        startActivity(intent);
    }
    public void onSignOff(View view){
        SessionManager.logOutUser(this);
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void returnToMainMenu(View view){
        Intent intent=new Intent(this, MainMenu.class);
        startActivity(intent);
        finish();
    }

}