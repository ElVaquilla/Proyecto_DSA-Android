package com.example.login;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.example.login.ModelosDeClases.Credenciales;
import com.example.login.ModelosDeClases.CredencialesRespuesta;
import com.example.login.ModelosDeClases.Jugador;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

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
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

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
                    Toast.makeText(LoginActivity.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, Tienda.class));
                }
                else {
                    Toast.makeText(LoginActivity.this, "Error, response is not as expected", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<CredencialesRespuesta> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error no response", Toast.LENGTH_SHORT).show();
            }
        });

    }
}