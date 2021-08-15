package com.example.bet.service;

import com.example.bet.model.RequestDevice;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Device_Service {
    @POST("oracle/insererDevice")
    Call<String> insererDevice(@Body RequestDevice device);
}
