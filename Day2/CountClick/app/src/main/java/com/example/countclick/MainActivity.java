package com.example.countclick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int countClicks=0;
    private TextView showCount;
    private final String TAG ="MainActivity";
    private final String TEXT_CONTENTS="TextContents";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showCount=(TextView) findViewById(R.id.show_count);
        if(savedInstanceState!=null){
            String savedString=savedInstanceState.get(TEXT_CONTENTS).toString();
            countClicks=Integer.parseInt(savedString);
        }
        Log.d(TAG, "onCreate: End");
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: Start");
        super.onStart();
        Log.d(TAG, "onStart: End");
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: Start");
        super.onResume();
        Log.d(TAG, "onResume: End");
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: Start");
        super.onStop();
        Log.d(TAG, "onStop: End");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: Start");
        super.onPause();
        Log.d(TAG, "onPause: End");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: Start");
        super.onDestroy();
        Log.d(TAG, "onDestroy: End");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        Log.d(TAG, "onRestore: Start");
        super.onRestoreInstanceState(savedInstanceState);
        String savedString=savedInstanceState.get(TEXT_CONTENTS).toString();
        showCount.setText(savedString);
        Log.d(TAG, "onRestore: End");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d(TAG, "onSave: Start");
        outState.putString(TEXT_CONTENTS,showCount.getText().toString());
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSave: End");
    }

    public void showToast(View view){
        Toast.makeText(MainActivity.this, R.string.welcome_message,
                Toast.LENGTH_SHORT).show();
    }
    public void showCountClick(View view){
        countClicks++;
        showCount.setText(Integer.toString(countClicks));
    }

}