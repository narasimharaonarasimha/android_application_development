package com.example.todoapp.data;

import android.provider.BaseColumns;

public class TodoContract {
    //one inner class per table
    public static final class TodosEntry implements BaseColumns{
        //Table name
        public static final String TABLE_NAME="todos";
        //column names
        public static final String _ID=BaseColumns._ID;
        public static final String COLUMN_TEXT="text";
        public static final String COLUMN_CREATED="created";
        public static final String COLUMN_EXPIRED="expired";
        public static final String COLUMN_DONE="done";
        public static final String COLUMN_CATEGORY="category";
    }
    //one inner class per table
    public static final class CategoriesEntry implements BaseColumns{
        //Table name
        public static final String TABLE_NAME="categories";
        //column names
        public static final String _ID=BaseColumns._ID;
        public static final String COLUMN_DESCRIPTION="description";

    }
}
