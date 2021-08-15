package com.example.bet.service;

import com.example.bet.model.RequestUtilisateur;
import com.example.bet.model.Utilisateur;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Utilisateur_Service {
    @POST("oracle/authentification")
    Call<Utilisateur> authentification(@Query("mail") String mail, @Query("pwd") String pwd);

    @GET("oracle/getUserbyId/{id}")//A changer lorsque l'APi sera disponible
    Call<Utilisateur> getByID(@Path("id") String id);

    @Headers("Content-Type: application/json")
    @POST("oracle/insertUser")

    Call<ResponseBody> inscription(@Body RequestUtilisateur utilisateur);


}
