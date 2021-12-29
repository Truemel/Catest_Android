package com.example.catestandroid.model;

import com.example.catestandroid.extras.GlobalConstants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CatRetroRequestsInterface {
    @Headers("x-api-key: "+ GlobalConstants.API_KEY)
    @GET("images/search?")
    Call<List<Cat>> onCatGot(@Query("breed_ids") String breedId);

    @Headers("x-api-key: 836b953e-953b-407c-a015-242b0fe495d2")
    @GET("breeds?")
    Call<List<CatBreed>> onBreedsGot(@Query("limit") int limit, @Query("page") int page);
}
