package com.example.bet.service;

import com.example.bet.modele.Equipe_Response;
import com.example.bet.modele.Match_Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Match_Service {
    //Match
    @GET("matches/getAll")//A changer lorsque l'APi sera disponible
    Call<Match_Response> getAllMatches();

    @POST("equipes/")
    Call<Match_Response> getTopRatedMovies(@Query("api_key") String apiKey, @Query("page") int pageIndex);
    @GET("movie/upcoming")
    Call<Match_Response> getUpComing(@Query("api_key") String apiKey, @Query("page") int pageIndex);
    @GET("search/movie")
    Call<Match_Response> searchMovies(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") int pageIndex);
}
