package com.example.activitytofragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText weight, height;
    Button calculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        MyFirstFragment myFirstFragment=new MyFirstFragment();

        weight=(EditText) findViewById(R.id.editTextWeight);
        height=(EditText) findViewById(R.id.editTextHeight);
        calculate=(Button) findViewById(R.id.buttonCalculate);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();

                int userWeight=Integer.valueOf(weight.getText().toString());
                int userHeight=Integer.valueOf(weight.getText().toString());

                bundle.putInt("weight", userWeight);
                bundle.putInt("height", userHeight);
                myFirstFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.frameLayout, myFirstFragment);
                fragmentTransaction.commit();
            }
        });
    }
}