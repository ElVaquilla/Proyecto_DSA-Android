package com.example.login;

import com.example.login.ModelosDeClases.Question;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface QuestionService {
    @POST("dsaApp/question")
    Call<Question> addQuestion(@Body Question question);
}
