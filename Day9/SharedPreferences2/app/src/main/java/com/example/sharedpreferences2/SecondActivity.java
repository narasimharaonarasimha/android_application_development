package com.example.sharedpreferences2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView textViewID, textViewName;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textViewID=(TextView) findViewById(R.id.textViewStudentID);
        textViewName=(TextView) findViewById(R.id.textViewStudentName);

    }

    public void loadDataFromMain(View view) {
        sharedPreferences=getSharedPreferences(getPackageName() + ".student_info",Context.MODE_PRIVATE);

        int student_id=sharedPreferences.getInt("student_id", 0);
        String student_name=sharedPreferences.getString("student_name", null);

        textViewID.setText("" + student_id);
        textViewName.setText(student_name);
           }
}