package com.example.bet.service;

import com.example.bet.modele.RequestDevice;
import com.example.bet.modele.RequestPari;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Device_Service {


    @POST("oracle/insererDevice")
    Call<String> insererDevice(@Body RequestDevice device);
}
