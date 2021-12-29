package com.example.catestandroid.view;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.catestandroid.R;

public class BreedDialogFragment extends DialogFragment {

    private TextView text;
    private String breed;

    public BreedDialogFragment(String breed){
        this.breed = breed;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = requireActivity().getLayoutInflater().inflate(R.layout.breed_dialog_layout, null);
        text = view.findViewById(R.id.cat);
        text.setText(breed);
        builder.setView(view);

        return builder.create();
    }

}
