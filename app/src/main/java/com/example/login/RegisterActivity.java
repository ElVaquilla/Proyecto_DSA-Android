package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.example.login.ModelosDeClases.Credenciales;
import com.example.login.ModelosDeClases.CredencialesRegistro;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
        // Recogemos los datos introducidos por el usuario
        Log.i("OnClick", "Entra en el login");
        EditText editText = (EditText) findViewById(R.id.username);
        String usrname = editText.getText().toString();
        EditText editText2 = (EditText) findViewById(R.id.password);
        String pswd = editText2.getText().toString();
        EditText editText3 = (EditText) findViewById(R.id.email);
        String mail = editText3.getText().toString();
        CredencialesRegistro c = new CredencialesRegistro(usrname, pswd,mail);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RegisterService register = retrofit.create(RegisterService.class);
        Call<List<CredencialesRegistro>> call = register.CreateCredencialesRegistro(c);
        call.enqueue(new Callback<List<CredencialesRegistro>>() {
            @Override
            public void onResponse(Call<List<CredencialesRegistro>> call, Response<List<CredencialesRegistro>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(RegisterActivity.this,"Error, response is not as expected", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<List<CredencialesRegistro>> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error no response", Toast.LENGTH_SHORT).show();
            }
        });
    }
}