package com.example.bet.service;

import com.example.bet.model.Equipe_Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Equipe_Service {


    //Equipe
    @GET("api/equipes/getAll")//A changer lorsque l'APi sera disponible
    Call<Equipe_Response> getAllEquipe();

    @POST("api/equipes/")
    Call<Equipe_Response> getTopRatedMovies(@Query("api_key") String apiKey, @Query("page") int pageIndex);
    @GET("movie/upcoming")
    Call<Equipe_Response> getUpComing(@Query("api_key") String apiKey, @Query("page") int pageIndex);
    @GET("search/movie")
    Call<Equipe_Response> searchMovies(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") int pageIndex);
}
