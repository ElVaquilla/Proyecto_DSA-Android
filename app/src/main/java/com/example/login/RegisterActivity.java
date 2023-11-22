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
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private Window window;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.window = getWindow();
    }

    public void registeronClick(View v) {
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
                    EditText editText3 = (EditText) findViewById(R.id.mail);
                    String mail = editText3.getText().toString();
                    CredencialesRegistro c = new CredencialesRegistro(usrname, pswd, mail);

                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://10.0.2.2/dsaApp/")
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    RegisterService register = retrofit.create(RegisterService.class);
                    Call<List<CredencialesRegistro>> call = register.CreateCredencialesRegistro(c);
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}