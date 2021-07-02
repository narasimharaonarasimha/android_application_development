package com.example.sharedpreferencesexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextAddress;
    private Button buttonCount;
    private CheckBox checkBoxStatus;
    private int count=0;
    private String name, address;
    private boolean status;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName= (EditText) findViewById(R.id.editTextName);
        editTextAddress= (EditText) findViewById(R.id.editTextAddress);
        buttonCount=(Button) findViewById(R.id.buttonCount);
        checkBoxStatus= (CheckBox) findViewById(R.id.checkBoxRemember);

        buttonCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                buttonCount.setText("" +count);
            }
        });
        retrieveData();
    }

    private void retrieveData() {
        sharedPreferences=getSharedPreferences("saveData", Context.MODE_PRIVATE);
        name=sharedPreferences.getString("name", null);
        address=sharedPreferences.getString("address", null);
        count=sharedPreferences.getInt("count",0);
        status=sharedPreferences.getBoolean("status", false);

        editTextName.setText(name);
        editTextAddress.setText(address);
        buttonCount.setText(""+count);
        if(status)
            checkBoxStatus.setChecked(true);
        else
            checkBoxStatus.setChecked(false);
    }

    @Override
    protected void onPause() {
        saveData();
        super.onPause();
    }

    private void saveData() {
        sharedPreferences=getSharedPreferences("saveData", Context.MODE_PRIVATE);
        name=editTextName.getText().toString();
        address=editTextAddress.getText().toString();
        if(checkBoxStatus.isChecked())
            status=true;
        else
            status=false;
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("name", name);
        editor.putString("address", address);
        editor.putInt("count", count);
        editor.putBoolean("status", status);
        editor.commit();
    }
}