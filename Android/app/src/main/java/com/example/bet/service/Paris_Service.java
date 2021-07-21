package com.example.bet.service;

import com.example.bet.modele.Equipe_Response;
import com.example.bet.modele.RequestPari;
import com.example.bet.modele.Utilisateur;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Paris_Service {
    @GET("oracle/getAllParis")
//A changer lorsque l'APi sera disponible
    Call<Equipe_Response> getAllEquipe();

    //   @POST("oracle/insertPari")
    //  Call<ResponseBody> insertPari(@Query("idUtilisateur") String idUtilisateur, @Query("idMatch") String idMatch, @Query("matchRegle") String matchRegle, @Query("mise") float mise);
    @POST("oracle/insertPari")
    Call<ResponseBody> insertPari(@Body RequestPari paris);
}