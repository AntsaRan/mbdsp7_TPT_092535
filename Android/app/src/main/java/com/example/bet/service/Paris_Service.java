package com.example.bet.service;

import com.example.bet.modele.Equipe_Response;
import com.example.bet.modele.Paris_Response;
import com.example.bet.modele.RequestPari;
import com.example.bet.modele.Utilisateur;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Paris_Service {
    @GET("oracle/getAllParisbyUserM/{id}")
//A changer lorsque l'APi sera disponible http://grails-api.herokuapp.com/oracle/getAllParisbyUserM/25
    Call<Paris_Response> getAllParisByUser(@Path("id") String id);

    @POST("oracle/insertPari")
    Call<ResponseBody> insertPari(@Body RequestPari paris);

}