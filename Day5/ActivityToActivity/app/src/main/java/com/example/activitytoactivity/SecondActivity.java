package com.example.activitytoactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView name, email, phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent=getIntent();

        String userName=intent.getStringExtra("name");
        String userEmail=intent.getStringExtra("email");
        int userPhone=intent.getIntExtra("phone",0);

        name=(TextView) findViewById(R.id.textViewName);
        email=(TextView) findViewById(R.id.textViewEmail);
        phone=(TextView) findViewById(R.id.textViewPhone);

        name.setText("Name: " + userName);
        email.setText("Email: " + userEmail);
        phone.setText("Phone: " + Integer.toString(userPhone));

    }
}