package com.example.catestandroid.presenter;

import com.example.catestandroid.model.Cat;
import com.example.catestandroid.model.CatBreed;

import java.util.List;

public interface OnCatAndBreedRequestListener {
    public void onCatGot(Cat cat, int position, List<CatBreed> list);
    public void onBreedListGot(List<CatBreed> list);
}
