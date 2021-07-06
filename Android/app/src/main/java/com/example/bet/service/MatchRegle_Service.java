package com.example.bet.service;

import com.example.bet.modele.MatchRegle_Response;
import com.example.bet.modele.Match_Response;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MatchRegle_Service {
    //Match
    @GET("matchRegles/getAll")//A changer lorsque l'APi sera disponible
    Call<MatchRegle_Response> getAllMatchesRegle();
}
