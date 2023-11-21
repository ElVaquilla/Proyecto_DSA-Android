package com.example.login;

import com.example.login.LoginService;
import com.example.login.Credenciales;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import java.io.IOException;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.window = getWindow();
        Button button = (Button) findViewById(R.id.login);
        button.setOnClickListener(MainActivity.this);
    }


    @Override
    public void onClick(View v) {
        // Recogemos los datos introducidos por el usuario

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
            if(respuesta.equals("Succesful"))
                window.setStatusBarColor(Color.parseColor("#00701a"));
            else
                window.setStatusBarColor(Color.parseColor("#00702b"));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}