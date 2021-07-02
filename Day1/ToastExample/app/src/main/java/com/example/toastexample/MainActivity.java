package com.example.toastexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText person_name;
    private Button button_click_here;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        person_name=(EditText)findViewById(R.id.person_name);
        button_click_here=(Button) findViewById(R.id.button_click_here);
        button_click_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message=getString(R.string.welcome_message) +" " + person_name.getText().toString();
                Toast.makeText(MainActivity.this,message,
                        Toast.LENGTH_LONG).show();
            }
        });
    }
    public void show(View view){
        String message=R.string.welcome_message + person_name.getText().toString();
        Toast.makeText(this,message,
                Toast.LENGTH_LONG).show();
    }
}