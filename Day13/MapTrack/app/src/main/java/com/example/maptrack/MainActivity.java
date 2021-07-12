package com.example.maptrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editTextSource, editTextDestination;
    Button buttonFindTrack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextDestination=findViewById(R.id.et_destination);
        editTextSource=findViewById(R.id.et_source);
        buttonFindTrack=findViewById(R.id.bt_track);
        buttonFindTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String source=editTextSource.getText().toString().trim();
                String destination= editTextDestination.getText().toString().trim();

                if(source.equals("")||destination.equals("")){
                    Toast.makeText(getApplicationContext(),"Enter both locations",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    DisplayTrack(source, destination);
                }
            }
        });
    }
    private void DisplayTrack(String source, String destination) {
        try {
            Uri uri = Uri.parse("https://www.google.com/maps/dir/" +
                    source + "/" + destination);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }catch(ActivityNotFoundException ex){
            Log.d("MainActivity", "DisplayTrack: Error");
        }
    }
}