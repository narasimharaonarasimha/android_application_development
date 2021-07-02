package com.example.sharedpreferences3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView textViewDisplay;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewDisplay=(TextView) findViewById(R.id.textViewDisplayData);
    }
    public void saveData(View view) {
        List<String> emails=new ArrayList<>();
        emails.add("john@gmail.com");
        emails.add("john@ictkosovo.com");

        Student student=new Student(123,"John",emails);
        sharedPreferences=getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        Gson gson =new Gson();
        String jsonString=gson.toJson(student, Student.class);
        editor.putString("student_key", jsonString);
        editor.apply();


    }
    public void loadData(View view) {
        sharedPreferences=getPreferences(Context.MODE_PRIVATE);
        String jsonString=sharedPreferences.getString("student_key", null);
        Gson gson=new Gson();

        Student student=gson.fromJson(jsonString, Student.class);

        String str=student.getStudentID() + "\n" +
                student.getStudentName() + "\n" +
                student.getEmails().toString();

        textViewDisplay.setText(str);



    }


}