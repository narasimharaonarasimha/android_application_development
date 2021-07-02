package com.example.preferences2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView txvDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txvDisplay = (TextView) findViewById(R.id.txvDisplay);
    }

    public void saveObjectType(View view) {

        Employee employee = getEmployee();

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();

        // Serialization
        Gson gson = new Gson();
        String jsonStr = gson.toJson(employee, Employee.class);
        Log.i(TAG + " SAVE", jsonStr);

        prefsEditor.putString("employee_key", jsonStr);
        prefsEditor.apply();
    }

    public void loadObjectType(View view) {

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String jsonStr = sharedPreferences.getString("employee_key", "");
        Log.i(TAG + " LOAD", jsonStr);

        // Deserialization
        Gson gson = new Gson();
        Employee employeeObj = gson.fromJson(jsonStr, Employee.class);

        displayText(employeeObj);
    }

    private Employee getEmployee() {

        Employee employee = new Employee();
        employee.setName("John");
        employee.setEmpId(123);
        employee.setEmails(Arrays.asList("john@gmail.com","john@yahoo.com"));

        return employee;
    }

    private void displayText(Employee employeeObj) {

        if (employeeObj == null)
            return;

        String display = employeeObj.getEmpId()
                + "\n" + employeeObj.getName()
                + "\n" + employeeObj.getEmails().toString();

        txvDisplay.setText(display);
    }
}