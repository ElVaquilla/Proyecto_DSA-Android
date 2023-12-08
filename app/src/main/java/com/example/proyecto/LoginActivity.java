package com.example.proyecto;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto.ModelosDeClases.Credenciales;
import com.example.proyecto.ModelosDeClases.CredencialesRespuesta;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private Window window;
    public static String usrname;
    public static String pswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.window = getWindow();
    }

    public static String getUsername(){return usrname;}

    public void loginonClick(View v) {
        // Recogemos los datos introducidos por el usuario
        Log.i("OnClick", "Entra en el login");

        EditText editText = (EditText) findViewById(R.id.username);
        this.usrname = editText.getText().toString();
        EditText editText2 = (EditText) findViewById(R.id.password);
        this.pswd = editText2.getText().toString();
        Credenciales c = new Credenciales(this.usrname, this.pswd);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginService login = retrofit.create(LoginService.class);

        Call<CredencialesRespuesta> call = login.Createcredenciales(c);
        call.enqueue(new Callback<CredencialesRespuesta>() {

            @Override
            public void onResponse(Call<CredencialesRespuesta> call, Response<CredencialesRespuesta> response) {
                if (response.isSuccessful()) {
                    Context context=LoginActivity.this;
                    SessionManager.loginUser(context,usrname);
                    Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(context, MainMenu.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Error, usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CredencialesRespuesta> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error no connection with server", Toast.LENGTH_SHORT).show();
                t.printStackTrace();

            }
        });

    }
}