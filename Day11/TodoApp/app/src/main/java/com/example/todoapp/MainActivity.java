package com.example.todoapp;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
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

public class MainActivity extends AppCompatActivity
implements LoaderManager.LoaderCallbacks<Cursor> {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    ListView listView;
    Cursor cursor;
    private static final int URL_LOADER=0;
    private static int ALL_RECORDS=-1;
    //String[] itemname={"Get movie tickets", "Order pizza for tonight",
    //"Buy Groceries","Running session", "Call John"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getLoaderManager().initLoader(URL_LOADER, null, this);

       // DatabaseHelper helper=new DatabaseHelper(this);
       // SQLiteDatabase db=helper.getReadableDatabase();
      //  CreateToDo();
       // readData();
     //   updateToDo();
       // deleteToDo();
       // CreateCategory();
        setSupportActionBar(binding.toolbar);

        readData();
        TodosCursorAdapter adapter=new TodosCursorAdapter(this,
                cursor,false);

        listView=(ListView) findViewById(R.id.lvTodos);
        listView.setAdapter(adapter);

       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent=new Intent(MainActivity.this, TodoActivity.class);
                String content=(String) listView.getItemAtPosition(position);
                intent.putExtra("content", content);
                startActivity(intent);
            }
        });*/
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    private void CreateTestTodo(){
        for(int i=1;i<=20;i++){
            ContentValues values=new ContentValues();
            values.put(TodosEntry.COLUMN_TEXT,"Todo Item #" + i);
            values.put(TodosEntry.COLUMN_CATEGORY,1);
            values.put(TodosEntry.COLUMN_CREATED,"2021-07-07");
            values.put(TodosEntry.COLUMN_DONE,0);
            Uri uri=getContentResolver().insert(TodosEntry.CONTENT_URI, values);

        }
    }
private void CreateCategory(){
        ContentValues values=new ContentValues();
        values.put(CategoriesEntry.COLUMN_DESCRIPTION,"Work");
        Uri uri=getContentResolver().insert(CategoriesEntry.CONTENT_URI,values);
        Log.d("MainActivity", "CreateCategory: Inserted Category " + uri);

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
                TodosEntry.TABLE_NAME+"." +TodosEntry._ID,
                 TodosEntry.COLUMN_CREATED,
                 TodosEntry.COLUMN_EXPIRED,
                 TodosEntry.COLUMN_DONE,
                TodosEntry.COLUMN_CATEGORY};

        cursor=  getContentResolver().query(
                TodosEntry.CONTENT_URI,projection,
                null,null,null);


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
    private void deleteToDo(int id){

        String[] args={String.valueOf(id)};
        if(id==ALL_RECORDS){
            args=null;
        }

        int numRows=getContentResolver().delete(TodosEntry.CONTENT_URI,
                TodosEntry._ID +"=?", args);
        Log.d("MainActivity", "deleteToDo: ");

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
        if (id == R.id.action_delete_all_todos) {
            deleteToDo(ALL_RECORDS);
        }
        if(id==R.id.action_create_test_data){
            CreateTestTodo();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}