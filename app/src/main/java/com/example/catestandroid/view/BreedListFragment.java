package com.example.catestandroid.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catestandroid.R;
import com.example.catestandroid.model.CatBreed;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class BreedListFragment extends Fragment implements OnBreedSelectedListener {

    private RecyclerView recycler;
    private List<CatBreed> list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    /**
     * Agrega valor a variable
     */
    private void init(){
        Type listType = new TypeToken<List<CatBreed>>(){}.getType();
        list = new Gson().fromJson(getArguments().getString("breeds"), listType);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.breed_list_fragment_layout, container, false);
        recycler = view.findViewById(R.id.recy);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(new BreedListAdapter(getContext(), list, this));
        return view;
    }

    /**
     * Mostrar dialogo con el nombre de la raza de gatito
     * @param name
     */
    @Override
    public void onBreedClick(String name) {
        BreedDialogFragment dialog = new BreedDialogFragment(name);
        dialog.show(getParentFragmentManager(), "raza");
    }
}
