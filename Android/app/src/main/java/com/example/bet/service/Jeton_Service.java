package com.example.bet.service;

import com.example.bet.model.HistoriqueJetons_Response;
import com.example.bet.model.Jeton;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Jeton_Service {

    @GET("api/jetons")//A changer lorsque l'APi sera disponible
    Call<List<Jeton>> getPrixJeton();
    @GET("oracle/getHistoByUserM/{id}")
    Call<HistoriqueJetons_Response> getAllHistoriqueJetons(@Path("id") String id);
    @PUT("oracle/addJetonsUser/{id}")//A changer lorsque l'APi sera disponible
    Call<ResponseBody> ajoutJeton(@Path("id") String id,@Query("jetons") int jetons, @Query("montantTotal") int montantTotal);
 //   @Headers("Content-Type: application/json")
    @PUT("oracle/removeJetonsUser/{id}")//A changer lorsque l'APi sera disponible
    Call<ResponseBody> retirerJeton(@Path("id") String id,@Query("jetons") int jetons, @Query("montantTotal") int montantTotal);
}
