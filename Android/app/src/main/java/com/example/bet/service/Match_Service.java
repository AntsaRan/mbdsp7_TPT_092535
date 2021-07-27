package com.example.bet.service;

import com.example.bet.modele.Equipe_Response;
import com.example.bet.modele.Match_Response;
import com.example.bet.modele.Regle_Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Match_Service {
    //Match
    @GET("api/matches/getAll")//A changer lorsque l'APi sera disponible
    Call<Match_Response> getAllMatches();
    @GET("api/matches/{id}")//A changer lorsque l'APi sera disponible
    Call<Match_Response> getByID(@Path("id") String id);
}
