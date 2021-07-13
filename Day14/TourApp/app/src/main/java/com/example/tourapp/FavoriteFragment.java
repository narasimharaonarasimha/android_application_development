package com.example.tourapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FavoriteFragment extends Fragment {

    RecyclerView recyclerViewFavoriteCities;
    VacationSource vacationSource;
    public FavoriteFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite,
                container, false);
        recyclerViewFavoriteCities = view.findViewById(R.id.favorite_recycler_view);
        recyclerViewFavoriteCities.setLayoutManager(new
                LinearLayoutManager(getContext()));
        recyclerViewFavoriteCities.setHasFixedSize(true);
        vacationSource = VacationSource.getInstance();
        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(getContext(),
                vacationSource.getFavoriteCityList());
        recyclerViewFavoriteCities.setAdapter(favoriteAdapter);
        return view;
    }
}