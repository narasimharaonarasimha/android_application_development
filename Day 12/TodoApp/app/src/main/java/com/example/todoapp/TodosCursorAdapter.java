package com.example.todoapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.todoapp.data.TodoContract;

public class TodosCursorAdapter extends CursorAdapter {

    public TodosCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(
                R.layout.todo_list_item, parent, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView todoTextView=(TextView) view.findViewById(R.id.tvNote);
        int textColumn=cursor.getColumnIndex(TodoContract.TodosEntry.COLUMN_TEXT);
        String text=cursor.getString(textColumn);
        todoTextView.setText(text);


    }
}
