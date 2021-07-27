package com.example.bet.service;

import com.example.bet.modele.Match_Response;
import com.example.bet.modele.RequestUtilisateur;
import com.example.bet.modele.Utilisateur;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Utilisateur_Service {
    @POST("oracle/authentification")
    Call<Utilisateur> authentification(@Query("mail") String mail, @Query("pwd") String pwd);

    @Headers("Content-Type: application/json")
    @POST("oracle/insertUser")

    Call<ResponseBody> inscription(@Body RequestUtilisateur utilisateur);

}
