package com.example.bet.service;

import com.example.bet.modele.Equipe_Response;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Jeton_Service {

    @PUT("oracle/addJetonsUser/{id}")//A changer lorsque l'APi sera disponible
    Call<ResponseBody> ajoutJeton(@Path("id") String id,@Query("jetons") int jetons);
 //   @Headers("Content-Type: application/json")
    @PUT("oracle/removeJetonsUser/{id}")//A changer lorsque l'APi sera disponible
    Call<ResponseBody> retirerJeton(@Path("id") String id,@Query("jetons") int jetons);
}
