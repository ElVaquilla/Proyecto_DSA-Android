package com.example.login;

import com.example.login.ModelosDeClases.Avatar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AvatarService {
    @GET("dsaApp/avatares/listaAvatares/{username}")
    Call<ArrayList<Avatar>> getAvatares(@Path("username") String username);

    @PUT("dsaApp/jugadores/updateAvatar")
    Call<Void> updateAvatar(@Body Avatar avatar);

    @GET("dsaApp/avatares/{username}/{avatar}")
    Call<Avatar> getAvatar(@Path("username") String username, @Path("avatar") String a);
}
