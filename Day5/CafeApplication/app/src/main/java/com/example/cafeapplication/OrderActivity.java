package com.example.cafeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private TextView textView;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent=getIntent();
        String message= "Order Info: " +
                intent.getStringExtra(MainActivity.extra_message);
        textView=findViewById(R.id.textView);
        textView.setText(message);

        spinner= (Spinner)findViewById(R.id.label_spinner);
        if(spinner!=null){
            spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(spinner!=null){
            spinner.setAdapter(adapter);
        }
    }
    public void onRadioButtonClicked(View view){
        boolean checked= ((RadioButton)view).isChecked();
        switch (view.getId()){
            case R.id.same_day:
                if(checked)
                    displayToast(getString(R.string.same_day));
                break;
            case R.id.next_day:
                if(checked)
                    displayToast(getString(R.string.next_day));
                break;
            case R.id.pickup:
                if(checked)
                    displayToast(getString(R.string.pick_up));
                break;
        }
    }
    public void displayToast(String message){
        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String spinnerLabel=parent.getItemAtPosition(position).toString();
        displayToast(spinnerLabel);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}