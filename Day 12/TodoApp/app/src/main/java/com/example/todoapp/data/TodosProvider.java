package com.example.todoapp.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.todoapp.data.TodoContract.TodosEntry;

import static com.example.todoapp.data.TodoContract.*;
import static com.example.todoapp.data.TodoContract.CONTENT_AUTHORITY;
import static com.example.todoapp.data.TodoContract.PATH_CATEGORIES;
import static com.example.todoapp.data.TodoContract.PATH_TODOS;

public class TodosProvider extends ContentProvider {
    private DatabaseHelper helper;
    //constants for the operation
    private static final int TODOS = 1;
    private static final int TODOS_ID = 2;
    private static final int CATEGORIES = 3;
    private static final int CATEGORIES_ID = 4;
    //cursor
    Cursor cursor;
    //urimatcher
    private static final UriMatcher uriMatcher =
            new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_TODOS, TODOS);
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_TODOS + "/#", TODOS_ID);
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_CATEGORIES, CATEGORIES);
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_CATEGORIES + "/#",
                CATEGORIES_ID);
    }
    @Override
    public boolean onCreate() {
        helper=new DatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        //get database
        SQLiteDatabase db=helper.getReadableDatabase();

        int match=uriMatcher.match(uri);
        String inTables = TodosEntry.TABLE_NAME
                + " inner join "
                + CategoriesEntry.TABLE_NAME
                + " on " + TodosEntry.COLUMN_CATEGORY + " = "
                + CategoriesEntry.TABLE_NAME + "." + CategoriesEntry._ID;
        SQLiteQueryBuilder sqLiteQueryBuilder;
        switch (match){
            case TODOS:
                sqLiteQueryBuilder=new SQLiteQueryBuilder();
                sqLiteQueryBuilder.setTables(inTables);
                cursor= sqLiteQueryBuilder.query(db,
                        projection,null,null,
                        null,null,
                        sortOrder);
                break;
            case TODOS_ID:
                sqLiteQueryBuilder=new SQLiteQueryBuilder();
                sqLiteQueryBuilder.setTables(inTables);
                selection=TodosEntry._ID+"=?";
                selectionArgs=new String[]
                        {String.valueOf(ContentUris.parseId(uri))};

                cursor= sqLiteQueryBuilder.query(db,
                        projection,selection,selectionArgs,
                        null,null,
                        sortOrder);
                break;
            case CATEGORIES:
                cursor= db.query(CategoriesEntry.TABLE_NAME,
                        projection,null,null,null,null,
                        sortOrder);
                break;
            case CATEGORIES_ID:
                selection=CategoriesEntry._ID+"=?";
                selectionArgs=new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor= db.query(CategoriesEntry.TABLE_NAME,
                        projection,selection,selectionArgs,null,null,
                        sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Querying unknown URI" + uri);

        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int match=uriMatcher.match(uri);
        switch (match) {
            case TODOS:
                return insertRecord(uri, values, TodosEntry.TABLE_NAME);
            case CATEGORIES:
                return insertRecord(uri, values, CategoriesEntry.TABLE_NAME);
            default:
                throw new IllegalArgumentException("Querying unknown URI" + uri);
        }
    }

    private Uri insertRecord(Uri uri, ContentValues values, String tableName) {
        SQLiteDatabase db=helper.getWritableDatabase();
        long id=db.insert(tableName,null,values);
        if(id==-1){
            Log.d("Error", "insertRecord: URI error" +uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return ContentUris.withAppendedId(uri,id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int match=uriMatcher.match(uri);
        switch (match) {
            case TODOS:
                return deleteRecord(uri,
                        null, null,TodosEntry.TABLE_NAME);
            case TODOS_ID:
                return deleteRecord(uri,
                        selection, selectionArgs,TodosEntry.TABLE_NAME);
            case CATEGORIES:
                return deleteRecord(uri,
                        null, null,CategoriesEntry.TABLE_NAME);
            case CATEGORIES_ID:
                return deleteRecord(uri,
                        selection, selectionArgs,CategoriesEntry.TABLE_NAME);
            default:
                throw new IllegalArgumentException("Querying unknown URI" + uri);
        }
    }
    private int deleteRecord(Uri uri,
                             String selection,
                             String[] selectionArgs, String tableName) {
        SQLiteDatabase db=helper.getWritableDatabase();
        int id=db.delete(tableName, selection, selectionArgs);
        if(id==-1){
            Log.d("Error", "deleteRecord: URI error" +uri);
            return -1;
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return id;
    }
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int match=uriMatcher.match(uri);
        switch (match) {
            case TODOS:
                return updateRecord(uri, values,
                        selection, selectionArgs,TodosEntry.TABLE_NAME);
            case CATEGORIES:
                return updateRecord(uri, values,
                        selection, selectionArgs,CategoriesEntry.TABLE_NAME);
            default:
                throw new IllegalArgumentException("Querying unknown URI" + uri);
        }
    }
    private int updateRecord(Uri uri, ContentValues values,
                             String selection,
                             String[] selectionArgs, String tableName) {
        SQLiteDatabase db=helper.getWritableDatabase();
        int id=db.update(tableName,values, selection, selectionArgs);
        if(id==0){
            Log.d("Error", "updateRecord: URI error" +uri);
            return -1;
        }
        return id;
    }
}
