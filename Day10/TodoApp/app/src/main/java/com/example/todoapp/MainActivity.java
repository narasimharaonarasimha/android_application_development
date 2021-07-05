package com.example.todoapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.todoapp.data.DatabaseHelper;
import com.example.todoapp.data.TodoContract;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.todoapp.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static com.example.todoapp.data.TodoContract.*;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    ListView listView;
    String[] itemname={"Get movie tickets", "Order pizza for tonight",
    "Buy Groceries","Running session", "Call John"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       // DatabaseHelper helper=new DatabaseHelper(this);
       // SQLiteDatabase db=helper.getReadableDatabase();
      //  CreateToDo();
       // readData();
     //   updateToDo();
        deleteToDo();
        setSupportActionBar(binding.toolbar);

        listView=(ListView) findViewById(R.id.lvTodos);
        listView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.todo_list_item,
                R.id.tvNote, itemname));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent=new Intent(MainActivity.this, TodoActivity.class);
                String content=(String) listView.getItemAtPosition(position);
                intent.putExtra("content", content);
                startActivity(intent);
            }
        });
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void CreateToDo(){
        DatabaseHelper helper=new DatabaseHelper(this);
        SQLiteDatabase db=helper.getWritableDatabase();
        String query = "INSERT INTO todos ("
                + TodosEntry.COLUMN_TEXT + ","
                + TodosEntry.COLUMN_CATEGORY + ","
                + TodosEntry.COLUMN_CREATED + ","
                + TodosEntry.COLUMN_EXPIRED + ","
                + TodosEntry.COLUMN_DONE + ")"
                + " VALUES (\"Go to the gym\", 1, \"2021-07-03\", \"\", 0)";
        db.execSQL(query);
        ContentValues values=new ContentValues();
        values.put(TodosEntry.COLUMN_TEXT,"Order Pizzas");
        values.put(TodosEntry.COLUMN_CATEGORY,1);
        values.put(TodosEntry.COLUMN_CREATED,"2021-01-02");
        values.put(TodosEntry.COLUMN_DONE,0);
        long todo_id=db.insert(TodosEntry.TABLE_NAME,null, values);

    }
    private void readData(){
        DatabaseHelper helper=new DatabaseHelper(this);
        SQLiteDatabase db=helper.getReadableDatabase();
        String[] projection={TodosEntry.COLUMN_TEXT,
                 TodosEntry.COLUMN_CREATED,
                 TodosEntry.COLUMN_EXPIRED,
                 TodosEntry.COLUMN_DONE,
                TodosEntry.COLUMN_CATEGORY};
        String selection=TodosEntry.COLUMN_CATEGORY + "=?";
        String[] selectionArgs={"1"};
        Cursor cursor=  db.query(TodosEntry.TABLE_NAME,projection,
                        selection,selectionArgs,
                null,null,null);

        int i=cursor.getCount();
        Log.d("Record Count", "readData: Number of Records " + i);
        String rowContent="";
        while(cursor.moveToNext()){
            for(i=0;i<=4;i++){
                rowContent+=cursor.getString(i) + " - ";
            }
            Log.d("Row", "readData: " + rowContent);
            rowContent="";
        }
        cursor.close();
    }
    private void updateToDo(){
        int id=1;
        DatabaseHelper helper=new DatabaseHelper(this);
        SQLiteDatabase db=helper.getWritableDatabase();
        String[] args={String.valueOf(id)};
        ContentValues values=new ContentValues();
        values.put(TodosEntry.COLUMN_TEXT,"Go to the Mall");
        int numRows=db.update(TodosEntry.TABLE_NAME, values,
                TodosEntry._ID +"=?", args);
        db.close();


    }
    private void deleteToDo(){
        int id=2;
        DatabaseHelper helper=new DatabaseHelper(this);
        SQLiteDatabase db=helper.getWritableDatabase();
        String[] args={String.valueOf(id)};

        int numRows=db.delete(TodosEntry.TABLE_NAME,
                TodosEntry._ID +"=?", args);
        db.close();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_categories) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}