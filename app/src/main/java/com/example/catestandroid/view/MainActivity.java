package com.example.catestandroid.view;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.catestandroid.R;
import com.example.catestandroid.model.Cat;
import com.example.catestandroid.model.CatBreed;
import com.example.catestandroid.presenter.CatPresenter;
import com.example.catestandroid.presenter.OnCatAndBreedRequestListener;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnCatAndBreedRequestListener {

    private ProgressDialog progress;
    private CatPresenter catPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getBreeds();
    }

    /**
     * Mostrar progress dialog mientras le pide al presentador que pida la lista de razas de gatitos
     */
    private void getBreeds(){
        progress = ProgressDialog.show(this, "Loading", "Now Loading", true);
        catPresenter = new CatPresenter(this, this);
        catPresenter.breedListRequest(10, 0);
    }

    /**
     * Inicia el fragmento con la lista de razas de gatitos
     * @param breeds
     */
    private void init(List<CatBreed> breeds){
        BreedListFragment fragment = new BreedListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("breeds", new Gson().toJson(breeds));
        fragment.setArguments(bundle);
        changeFragment(fragment);
        progress.dismiss();
    }

    /**
     * Función para cambiar el fragmento a mostrar
     * @param fragment
     */
    public void changeFragment(Fragment fragment){
        if(!isChangingConfigurations())
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();
    }

    @Override
    public void onCatGot(Cat cat, int position, List<CatBreed> list) {
        list.get(position).cat = cat;
        if(position < list.size()-1)
            catPresenter.catRequest(list.get(position+1).id, position+1, list);
        else
            init(list);
    }

    @Override
    public void onBreedListGot(List<CatBreed> list) {
        catPresenter.catRequest(list.get(0).id, 0, list);
    }
}