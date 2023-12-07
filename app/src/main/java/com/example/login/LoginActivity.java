package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.example.login.ModelosDeClases.RespuestaLogin;
import com.example.login.SessionManager;

import com.example.login.ModelosDeClases.Credenciales;

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
        Call<RespuestaLogin> call = login.Createcredenciales(c);
        call.enqueue(new Callback<RespuestaLogin>() {

            @Override
            public void onResponse(Call<RespuestaLogin> call, Response<RespuestaLogin> response) {
                if (response.isSuccessful()) {
                    Context context=LoginActivity.this;
                    SessionManager.loginUser(context,usrname);
                    Toast.makeText(LoginActivity.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(context, MainMenu.class);
                    startActivity(intent);
                    finish();
                }
                else
                    Toast.makeText(LoginActivity.this, "Error, response is not as expected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<RespuestaLogin> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error No response", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }
}