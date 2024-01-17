package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.ModelosDeClases.Jugador;
import com.example.login.ModelosDeClases.Question;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddQuestion extends AppCompatActivity {
    private ProgressBar spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
    }

    public  void exitOnClick(View view){
        finish();
    }

    public void sendOnClick(View view) {
        spinner = (ProgressBar) findViewById(R.id.progressBar3);


        EditText title = findViewById(R.id.editTextTitle);
        MultiAutoCompleteTextView message = findViewById(R.id.multiAutoCompleteTextView);

        if (title.getText().toString().trim().isEmpty() || title.getText().toString().trim().isEmpty()){
            Toast.makeText(AddQuestion.this, "Both fields are mandatory", Toast.LENGTH_SHORT).show();

        } else{

            HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
            loggin.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(loggin);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://147.83.7.205:80/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            QuestionService question = retrofit.create(QuestionService.class);

            String username = SessionManager.getLoggedUsername(this);

            Question q = new Question(title.getText().toString(), message.getText().toString(), username);
            Call<Question> call = question.addQuestion(q);
            spinner.setVisibility(View.VISIBLE);
            call.enqueue(new Callback<Question>() {
                @Override
                public void onResponse(Call<Question> call, Response<Question> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(AddQuestion.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();
                        title.setText("");
                        message.setText("");
                        spinner.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(AddQuestion.this, "Error, response is not as expected", Toast.LENGTH_SHORT).show();
                        spinner.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<Question> call, Throwable t) {
                    Log.e("Retrofit", "Error: " + t.getMessage()); // Log the error message
                    Toast.makeText(AddQuestion.this, "Error No response", Toast.LENGTH_SHORT).show();
                    spinner.setVisibility(View.GONE);

                }
            });

        }
    }
}
