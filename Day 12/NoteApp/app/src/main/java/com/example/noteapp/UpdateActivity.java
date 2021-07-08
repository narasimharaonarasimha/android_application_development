package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {
    EditText title, description;
    Button save, cancel;
    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        getSupportActionBar().setTitle("Update Note");
        title=findViewById(R.id.editTextTitleUpdate);
        description=findViewById(R.id.editTextDescriptionUpdate);
        save=findViewById(R.id.buttonSaveUpdate);
        cancel=findViewById(R.id.buttonCancelUpdate);

        getData();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNote();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void updateNote() {
        String updateTitle=title.getText().toString();
        String updateDescription=description.getText().toString();
        Intent intent=new Intent();
        intent.putExtra("updatetitle", updateTitle);
        intent.putExtra("updatedescription", updateDescription);
        if(noteId!=-1){
            intent.putExtra("noteid", noteId);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
    public void getData(){
        Intent intent=getIntent();
        noteId=intent.getIntExtra("id", -1);
        String noteTitle=intent.getStringExtra("title");
        String noteDescription=intent.getStringExtra("description");
        title.setText(noteTitle);
        description.setText(noteDescription);
    }
}