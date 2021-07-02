package com.example.listviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listViewCountries;
    String countries[];
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewCountries=findViewById(R.id.listViewCountries);
        countries=getResources().getStringArray(R.array.countries);

        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,countries);

        listViewCountries.setAdapter(adapter);

        listViewCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String country= parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, "You selected " + country,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}