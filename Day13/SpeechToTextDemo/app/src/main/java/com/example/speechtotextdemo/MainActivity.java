package com.example.speechtotextdemo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView textViewResult;
    ImageButton imageButtonMic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult=findViewById(R.id.txvResult);
        imageButtonMic=findViewById(R.id.btnSpeak);

        imageButtonMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityResultLauncher<Intent> intentLaunch =
                        registerForActivityResult(
                                new ActivityResultContracts.StartActivityForResult(),
                                new ActivityResultCallback<ActivityResult>() {
                                    @Override
                                    public void onActivityResult(ActivityResult result) {
                                        if (result.getResultCode() == RESULT_OK) {
                                            ArrayList<String> speechResult =
                                                    result.getData()
                                                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                                            textViewResult.setText(speechResult.get(0));
                                        }
                                    }
                                });


                Intent intent=
                        new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

                if(intent.resolveActivity(getPackageManager())!=null) {
                    intentLaunch.launch(intent);
                }
                else{
                    Toast.makeText(MainActivity.this,
                            "Your device does not support speech",
                            Toast.LENGTH_SHORT).show();
                }

               /* if(intent.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(intent,1);
                }
                else{
                    Toast.makeText(MainActivity.this,
                            "Your device does not support speech",
                            Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK){
            ArrayList<String> speechResult=
            data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            textViewResult.setText(speechResult.get(0));
        }
    }*/
}