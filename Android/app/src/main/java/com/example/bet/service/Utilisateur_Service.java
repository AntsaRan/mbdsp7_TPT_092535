package com.example.bet.service;

import com.example.bet.modele.Match_Response;
import com.example.bet.modele.Utilisateur;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Utilisateur_Service {
    @POST("authentification/")
    Call<Utilisateur> authentification(@Query("mail") String apiKey, @Query("pwd") int pageIndex);
}
