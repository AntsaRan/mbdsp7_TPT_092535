package com.example.bet.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    public static String API_TOKEN = "c54b1b1aeb0a3fbf4385767c85a7085b";
    public static final String BASE_URL = "https://grails-api.herokuapp.com/api/";
    public static Retrofit retrofit = null;


    public static Retrofit getClient(){

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
