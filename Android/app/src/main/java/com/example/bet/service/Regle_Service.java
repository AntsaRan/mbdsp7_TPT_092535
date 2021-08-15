package com.example.bet.service;

import com.example.bet.model.Regle_Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Regle_Service {
    @GET("api/regles/{id}")//A changer lorsque l'APi sera disponible
    Call<Regle_Response> getByID(@Path("id") String id);
}
