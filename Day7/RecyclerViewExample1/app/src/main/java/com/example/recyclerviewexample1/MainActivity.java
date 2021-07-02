package com.example.recyclerviewexample1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;

    String[] countryNamesList;
    String[] detailsList;
    TypedArray imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerViewCountries);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        countryNamesList=getResources().getStringArray(R.array.countries);
        detailsList=getResources().getStringArray(R.array.details);
        imageList=getResources().obtainTypedArray(R.array.flags);

        recyclerAdapter=new RecyclerAdapter(countryNamesList,detailsList,
                imageList,MainActivity.this);

        recyclerView.setAdapter(recyclerAdapter);

    }
}