package com.example.sharedpreferences2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText editTextID, editTextName;
    private TextView textViewID, textViewName;
    private Switch pageColorSwitch;
    private LinearLayout pageLayout;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextID=(EditText) findViewById(R.id.editTextStudentID);
        editTextName=(EditText) findViewById(R.id.editTextStudentName);

        textViewID=(TextView) findViewById(R.id.textViewStudentID);
        textViewName=(TextView) findViewById(R.id.textViewStudentName);

        pageLayout=(LinearLayout) findViewById(R.id.pageLayout);
        pageColorSwitch=(Switch)findViewById(R.id.pageColorSwitch);

        pageColorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pageLayout.setBackgroundColor(pageColorSwitch.isChecked()?Color.YELLOW:Color.WHITE);
            }
        });

    }

    public void saveData(View view) {
        sharedPreferences=getSharedPreferences(getPackageName() + ".student_info",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("page_color", pageColorSwitch.isChecked());
        editor.putInt("student_id", Integer.valueOf(editTextID.getText().toString()));
        editor.putString("student_name", editTextName.getText().toString());
        editor.apply(); //editor.commit() - apply asynchronously
    }
    public void loadData(View view) {
        sharedPreferences=getSharedPreferences(getPackageName() + ".student_info",Context.MODE_PRIVATE);

        boolean page_color=sharedPreferences.getBoolean("page_color", false);
        int student_id=sharedPreferences.getInt("student_id", 0);
        String student_name=sharedPreferences.getString("student_name", null);

        textViewID.setText("" + student_id);
        textViewName.setText(student_name);
        pageLayout.setBackgroundColor(page_color?Color.YELLOW:Color.WHITE);
    }

    public void openSecondActivity(View view) {
        Intent intent=new Intent(MainActivity.this,SecondActivity.class);
        startActivity(intent);
    }
}