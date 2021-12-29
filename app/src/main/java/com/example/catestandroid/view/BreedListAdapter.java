package com.example.catestandroid.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catestandroid.R;
import com.example.catestandroid.model.Cat;
import com.example.catestandroid.model.CatBreed;
import com.example.catestandroid.presenter.CatPresenter;
import com.example.catestandroid.presenter.OnCatAndBreedRequestListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BreedListAdapter extends RecyclerView.Adapter<BreedListAdapter.Holder> implements OnCatAndBreedRequestListener, View.OnClickListener {

    private Context context;
    private ArrayList<CatBreed> list;
    private CatPresenter presenter;
    private ProgressDialog progress;
    private OnBreedSelectedListener onSelect;

    public BreedListAdapter(Context context, List<CatBreed> list, OnBreedSelectedListener onSelect){
        this.list = new ArrayList<>();
        this.context = context;
        this.list.addAll(list);
        this.onSelect = onSelect;
        presenter = new CatPresenter(this, context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.breed_list_adapter_layout, parent, false);
        Holder holder = new Holder(view);
        holder.image = view.findViewById(R.id.image);
        holder.itemView.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.itemView.setTag(list.get(position).name);
        if(list.get(position).cat != null && list.get(position).cat.url.length() > 0)
            Picasso.get().load(list.get(position).cat.url).into(holder.image);
        if(list.size()%10 == 0 && position == list.size()-1) {
            progress = ProgressDialog.show(context, "Loading", "Now Loading", true);
            presenter.breedListRequest(10, list.size() / 10);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(List<CatBreed> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    private void addNewElements(List<CatBreed> list){
        updateList(list);
        progress.dismiss();
    }

    @Override
    public void onCatGot(Cat cat, int position, List<CatBreed> list) {
        list.get(position).cat = cat;
        if(position < list.size()-1)
            presenter.catRequest(list.get(position+1).id, position+1, list);
        else
            addNewElements(list);
    }

    @Override
    public void onBreedListGot(List<CatBreed> list) {
        presenter.catRequest(list.get(0).id, 0, list);
    }

    @Override
    public void onClick(View v) {
        onSelect.onBreedClick(v.getTag().toString());
    }

    public class Holder extends RecyclerView.ViewHolder{

        public ImageView image;

        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
