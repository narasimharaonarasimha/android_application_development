package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase sqLiteDatabase=
                getBaseContext().openOrCreateDatabase("address_book.db",
                        MODE_PRIVATE,null);
        String sql="drop table if exists contacts";
        sqLiteDatabase.execSQL(sql);

        sql="create table if not exists contacts " +
                "(name text, phone integer,email text)";

        sqLiteDatabase.execSQL(sql);

        sql="insert into contacts values('john', 123123,'john@gmail.com')";
        sqLiteDatabase.execSQL(sql);
        sql="insert into contacts values('jack', 234234,'jack@gmail.com')";
        sqLiteDatabase.execSQL(sql);

        sql="select * from contacts";
        Cursor query=sqLiteDatabase.rawQuery(sql,null);

        if(query.moveToFirst()){
            do{
                String name=query.getString(0);
                int phone=query.getInt(1);
                String email=query.getString(2);
                Toast.makeText(this,"Name: " +name + " Phone: " +phone
                +" Email: " + email, Toast.LENGTH_SHORT).show();
            }while(query.moveToNext());
        }
        query.close();
        sqLiteDatabase.close();
    }
}