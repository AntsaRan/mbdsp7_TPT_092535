package com.example.bet.service;

import com.example.bet.model.Match_Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Match_Service {
    //Match
    @GET("api/matches/getAll")//A changer lorsque l'APi sera disponible
    Call<Match_Response> getAllMatches();
    @GET("api/matchespardateM/{date}")//A changer lorsque l'APi sera disponible
    Call<Match_Response> getAllMatchesByDate(@Path("date") String date);
    @GET("api/matches/{id}")//A changer lorsque l'APi sera disponible
    Call<Match_Response> getByID(@Path("id") String id);
}
