package com.example.preferences1;

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
    private EditText etName, etProfession;
    private TextView txvName, txvProfession;
    private Switch pageColorSwitch;
    private LinearLayout pageLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etProfession = (EditText) findViewById(R.id.etProfession);

        txvName = (TextView) findViewById(R.id.txvName);
        txvProfession = (TextView) findViewById(R.id.txvProfession);

        pageLayout = (LinearLayout) findViewById(R.id.pageLayout);

        pageColorSwitch = (Switch) findViewById(R.id.pageColorSwitch);
        pageColorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setPageColor(isChecked);
            }
        });
        // Load Data from Activity Level SharedPrefs
        SharedPreferences sharedPreferences =
                getSharedPreferences(getPackageName()+Constants.PREF_FILE_NAME,
                        Context.MODE_PRIVATE);
        boolean isChecked = sharedPreferences.getBoolean(Constants.KEY_PAGE_COLOR,
                false);
        pageColorSwitch.setChecked(isChecked);
    }
    private void setPageColor(boolean isChecked) { // Save data to Activity Level SharedPrefs

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName()+Constants.PREF_FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Constants.KEY_PAGE_COLOR, isChecked);
        editor.apply(); // editor.commit();

        pageLayout.setBackgroundColor(isChecked? Color.GREEN : Color.WHITE);
    }

    public void openSecondActivity(View view) {

        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void saveAccountData(View view) { // Save data to Application Level SharedPrefs

        SharedPreferences sharedPreferences =
                getSharedPreferences(getPackageName()+Constants.PREF_FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(Constants.KEY_NAME, etName.getText().toString());
        editor.putString(Constants.KEY_PROFESSION, etProfession.getText().toString());
        editor.apply(); // editor.commit()
    }

    public void loadAccountData(View view) { // Load data from Application Level SharedPrefs

        SharedPreferences sharedPreferences =
                getSharedPreferences(getPackageName()+
                        Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);

        String name = sharedPreferences.getString(Constants.KEY_NAME, "N/A");
        String profession = sharedPreferences.getString(Constants.KEY_PROFESSION, "N/A");

        txvName.setText(name);
        txvProfession.setText(profession);
    }
}