package com.example.catestandroid.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class CatRetroRequest {

    private CatRetroRequestsInterface retroReq;

    public CatRetroRequest(){
        retroReq = RetrofitClient.getRetrofit().create(CatRetroRequestsInterface.class);
    }

    public void getBreedsList(int limit, int page, Callback<List<CatBreed>> callback){
        Call<List<CatBreed>> call = retroReq.onBreedsGot(limit, page);
        call.enqueue(callback);
    }

    public void getCat(Callback<List<Cat>> callback, String breedId){
        Call<List<Cat>> call = retroReq.onCatGot(breedId);
        call.enqueue(callback);
    }
}
