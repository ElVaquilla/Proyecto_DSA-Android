package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.window = getWindow();
    }

    public void loginonClick(View v) {

        // Recogemos los datos introducidos por el usuario
        Log.i("OnClick", "Entra en el login");
        EditText editText = (EditText) findViewById(R.id.username);
        String usrname = editText.getText().toString();
        EditText editText2 = (EditText) findViewById(R.id.password);
        String pswd = editText2.getText().toString();
        Credenciales c = new Credenciales(usrname, pswd);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginService login = retrofit.create(LoginService.class);
        Call<List<Credenciales>> call = login.Createcredenciales(c);
        call.enqueue(new Callback<List<Credenciales>>() {
            @Override
            public void onResponse(Call<List<Credenciales>> call, Response<List<Credenciales>> response) {
                if (response.isSuccessful()) {
                    window.setStatusBarColor(Color.parseColor("#00701a"));
                } else {
                    window.setStatusBarColor(Color.parseColor("#00702b"));
                }
            }

            @Override
            public void onFailure(Call<List<Credenciales>> call, Throwable t) {
                throw new RuntimeException(t);
            }
        });

    }


       /*
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Recogemos los datos introducidos por el usuario
                    Log.i("OnClick", "Entra en el login");
                    EditText editText = (EditText) findViewById(R.id.username);
                    String usrname = editText.getText().toString();
                    EditText editText2 = (EditText) findViewById(R.id.password);
                    String pswd = editText2.getText().toString();
                    Credenciales c = new Credenciales(usrname, pswd);

                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://10.0.2.2/dsaApp/")
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    LoginService login = retrofit.create(LoginService.class);
                    Call<List<Credenciales>> call = login.Createcredenciales(c);
                    String respuesta = null;
                    try {
                        respuesta = call.execute().body().toString();
                        if (respuesta.equals("Succesful"))
                            window.setStatusBarColor(Color.parseColor("#00701a"));
                        else
                            window.setStatusBarColor(Color.parseColor("#00702b"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

    */
}