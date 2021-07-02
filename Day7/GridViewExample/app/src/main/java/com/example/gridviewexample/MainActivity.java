package com.example.gridviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    GridAdapter gridAdapter;

    String[] countryNamesList;
    TypedArray imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countryNamesList=getResources().getStringArray(R.array.countries);
        imageList=getResources().obtainTypedArray(R.array.flags);

        gridView=findViewById(R.id.gridView);
        gridAdapter=new GridAdapter(MainActivity.this,countryNamesList,imageList);

        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, " You selected "
                        + countryNamesList[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}