package com.example.catestandroid.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String PATH = "https://api.thecatapi.com/v1/";

    private static Retrofit retro;

    private RetrofitClient(){
        retro = new Retrofit.Builder().baseUrl(PATH).addConverterFactory(GsonConverterFactory.create()).build();
    }


    public static Retrofit getRetrofit(){
        synchronized (RetrofitClient.class){
            if(retro == null)
                new RetrofitClient();
        }
        return retro;
    }

}
