package com.example.todoapp.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class TodoContract {
    //URI section
    public static final String CONTENT_AUTHORITY="com.example.todoapp.todosprovider";

    public static final String PATH_TODOS="todos";
    public static final String PATH_CATEGORIES="categories";

    public static final Uri BASE_CONTENT_URI=Uri.parse("content://"
    + CONTENT_AUTHORITY);

    //one inner class per table
    public static final class TodosEntry implements BaseColumns{
        public static final Uri CONTENT_URI=
                Uri.withAppendedPath(BASE_CONTENT_URI, PATH_TODOS);
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
        public static final Uri CONTENT_URI=
                Uri.withAppendedPath(BASE_CONTENT_URI, PATH_CATEGORIES);
        //Table name
        public static final String TABLE_NAME="categories";
        //column names
        public static final String _ID=BaseColumns._ID;
        public static final String COLUMN_DESCRIPTION="description";

    }
}
