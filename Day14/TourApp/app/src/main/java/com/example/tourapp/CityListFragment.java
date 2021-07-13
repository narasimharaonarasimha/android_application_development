package com.example.tourapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class CityListFragment extends Fragment {

    RecyclerView recyclerViewCities;
    VacationSource vacationSource;
    public CityListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_city_list,
                container, false);
        recyclerViewCities=view.findViewById(R.id.city_recycler_view);
        recyclerViewCities.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewCities.setHasFixedSize(true);
        vacationSource=VacationSource.getInstance();
        CityAdapter cityAdapter=new CityAdapter(getContext(),
                vacationSource.getCityList());
        recyclerViewCities.setAdapter(cityAdapter);
        return view;

    }
}