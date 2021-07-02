package com.example.datepickerexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void showDatePicker(View view){
        DialogFragment dialogFragment=new DatePickerFragment();
        dialogFragment.show(getSupportFragmentManager(),"datePicker");
    }
    public void showTimePicker(View view){
        DialogFragment dialogFragment=new TimePickerFragment();
        dialogFragment.show(getSupportFragmentManager(),"timePicker");
    }
    public void processDatePickerResult(int year, int month, int day){
        String month_string=Integer.toString(month+1);
        String day_string=Integer.toString(day);
        String year_string=Integer.toString(year);

        String message=(day_string + "-" + month_string + "-" + year_string);
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
    public void processTimePickerResult(int hour, int minute){
        String hour_string=Integer.toString(hour);
        String minute_string=Integer.toString(minute);

        String message=(hour_string + ":" + minute_string );
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}