package com.example.fragmenttoactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView name, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        MyFirstFragment myFirstFragment=new MyFirstFragment();
        fragmentTransaction.add(R.id.linearLayout, myFirstFragment);
        fragmentTransaction.commit();
    }
    public void collectData(String userName, String userEmail){
        name=(TextView) findViewById(R.id.textViewName);
        email= (TextView) findViewById(R.id.textViewEmailAddress);

        name.setText("Name: " + userName);
        email.setText("Email: " + userEmail);

    }
}