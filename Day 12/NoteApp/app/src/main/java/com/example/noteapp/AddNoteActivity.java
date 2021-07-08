package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNoteActivity extends AppCompatActivity {
    EditText title, description;
    Button save, cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Note");
        setContentView(R.layout.activity_add_note);

        title=findViewById(R.id.editTextTitle);
        description=findViewById(R.id.editTextDescription);
        save=findViewById(R.id.buttonSave);
        cancel=findViewById(R.id.buttonCancel);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void saveNote(){
        String noteTitle=title.getText().toString();
        String noteDescription=description.getText().toString();

        Intent intent=new Intent();
        intent.putExtra("title", noteTitle);
        intent.putExtra("description", noteDescription);
        setResult(RESULT_OK, intent);
        finish();

    }
}