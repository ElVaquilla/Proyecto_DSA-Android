package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.login.R;
import com.example.proyecto.ModelosDeClases.CredencialesRegistro;
import com.example.proyecto.ModelosDeClases.CredencialesRespuesta;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private Window window;
    private ProgressBar spinner;
    private Button registerButton;
    public static String pswd;
    public static String usrname;

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
        this.usrname = editText.getText().toString();
        EditText editText2 = (EditText) findViewById(R.id.password);
        this.pswd = editText2.getText().toString();
        EditText editText3 = (EditText) findViewById(R.id.email);
        String mail = editText3.getText().toString();
        EditText editText4=(EditText)findViewById(R.id.confirmPassword);
        String confirmPswd=editText4.getText().toString();

        registerButton=(Button)findViewById(R.id.reg);
        spinner=(ProgressBar)findViewById(R.id.progressBar);

        if(pswd.equals(confirmPswd)){
            CredencialesRegistro c = new CredencialesRegistro(this.usrname, this.pswd,mail);

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8080/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RegisterService register = retrofit.create(RegisterService.class);

            Call<CredencialesRespuesta> call = register.CreateCredencialesRegistro(c);
            spinner.setVisibility(View.VISIBLE);
            call.enqueue(new Callback<CredencialesRespuesta>() {
                @Override
                public void onResponse(Call<CredencialesRespuesta> call, Response<CredencialesRespuesta> response) {
                    if (response.isSuccessful()) {
                        spinner.setVisibility(View.GONE);
                        Context context=RegisterActivity.this;
                        SessionManager.registerUser(context, usrname);

                        Toast.makeText(RegisterActivity.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(context, MainMenu.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                        Toast.makeText(RegisterActivity.this,"Error, response is not as expected", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onFailure(Call<CredencialesRespuesta> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, "Error no response", Toast.LENGTH_SHORT).show();
                    spinner.setVisibility(View.GONE);
                }
            });
            registerButton.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    spinner.setVisibility(View.VISIBLE);
                }
            }));
        }else{
            editText.setText("");
            editText2.setText("");
            editText3.setText("");
            editText4.setText("");
            registerButton.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    spinner.setVisibility(View.VISIBLE);
                }
            }));
            Toast.makeText(RegisterActivity.this, "Error, las contraseñas no coinciden", Toast.LENGTH_SHORT).show();

        }


    }
}