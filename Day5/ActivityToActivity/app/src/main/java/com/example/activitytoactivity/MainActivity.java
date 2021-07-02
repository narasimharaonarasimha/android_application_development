package com.example.activitytoactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText name, email, phone;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=(EditText) findViewById(R.id.editTextName);
        email=(EditText) findViewById(R.id.editTextEmailAddress);
        phone=(EditText) findViewById(R.id.editTextPhone);
        signup=(Button) findViewById(R.id.buttonSignup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=name.getText().toString();
                String userEmail=email.getText().toString();
                int userPhone=Integer.valueOf(phone.getText().toString());

                Intent intent=new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("name", userName);
                intent.putExtra("email", userEmail);
                intent.putExtra("phone", userPhone);
                startActivity(intent);
            }
        });


    }
}