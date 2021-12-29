package com.example.catestandroid.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.catestandroid.model.Cat;
import com.example.catestandroid.model.CatBreed;
import com.example.catestandroid.model.CatRetroRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatPresenter {

    private CatRetroRequest request;
    private OnCatAndBreedRequestListener listener;
    private Context context;

    public CatPresenter(OnCatAndBreedRequestListener listener, Context context){
        this.listener = listener;
        this.context = context;
        request = new CatRetroRequest();
    }

    public void breedListRequest(int limit, int page){
        request.getBreedsList(limit, page, new Callback<List<CatBreed>>() {
            @Override
            public void onResponse(Call<List<CatBreed>> call, Response<List<CatBreed>> response) {
                if(response != null && response.isSuccessful())
                    listener.onBreedListGot(response.body());
            }

            @Override
            public void onFailure(Call<List<CatBreed>> call, Throwable t) {
                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void catRequest(String breedId, int position, List<CatBreed> list){
        request.getCat(new Callback<List<Cat>>() {
            @Override
            public void onResponse(Call<List<Cat>> call, Response<List<Cat>> response) {
                if(response != null && response.isSuccessful())
                    listener.onCatGot(response.body().get(0), position, list);
            }

            @Override
            public void onFailure(Call<List<Cat>> call, Throwable t) {
                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
                listener.onCatGot(null, position, list);
            }
        }, breedId);
    }
}
