package com.example.cargame;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class fragment_list extends Fragment {

    private RecyclerView main_LST_recyclerView;
    private ArrayList<Save> save;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        main_LST_recyclerView = view.findViewById(R.id.main_LST_recyclerView);

        save = SaveData.getSAveArr();

        Adapter_Save saveAdapter = new Adapter_Save(this, save);
        main_LST_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        main_LST_recyclerView.setAdapter(saveAdapter);

        return view;
        }

}