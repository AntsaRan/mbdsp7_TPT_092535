package com.example.bet.service;

import com.example.bet.model.MatchRegle_Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MatchRegle_Service {
    //Match
    @GET("api/matchRegles/getAll")//A changer lorsque l'APi sera disponible
    Call<MatchRegle_Response> getAllMatchesRegle();

    @GET("api/matchRegle/{id}")//A changer lorsque l'APi sera disponible
    Call<MatchRegle_Response> getByID(@Path("id") String id);
}
