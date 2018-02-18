package com.example.arti.todoapp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by bandriya on 18-Feb-18.
 */
public class TodoProvider extends ContentProvider {

    public final String LOG_TAG="TodoProvider.java";
    /**
     * Initialize the provider and the database helper object.
     */
    TodoDbHelper mDbHelper;
    private static final int TODOS=100;
    private static final UriMatcher sUriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(TodoContract.CONTENT_AUTHORITY, TodoContract.PATH_TODOS, TODOS);
    }
        @Override
    public boolean onCreate() {

        // Make sure the variable is a global variable, so it can be referenced from other
        // ContentProvider methods.
        mDbHelper=new TodoDbHelper(getContext());
        return true;
    }
    
    
    /**
     * Perform the query for the given URI. Use the given projection, selection, selection arguments, and sort order.
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {

        
        Cursor cursor;

        SQLiteDatabase database=mDbHelper.getReadableDatabase();
        int match=sUriMatcher.match(uri);
        switch(match)
        {
            case TODOS:

                // For the TodoS code, query the Todos table directly with the given
                // projection, selection, selection arguments, and sort order. The cursor
                // could contain multiple rows of the Todos table.
                cursor=database.query(TodoContract.TodoEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                throw new IllegalArgumentException("cannot query unknown URI"+uri);
        }
        return cursor;
    }

    /**
     * Insert new data into the provider with the given ContentValues.
     */
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        int match=sUriMatcher.match(uri);


        switch (match)
        {
            case TODOS:
                return insertTodo(uri,contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }
    private Uri insertTodo(Uri uri, ContentValues values) {


        String title=values.getAsString(TodoContract.TodoEntry.COLUMN_TODO_TITLE);
        if (title == null) {
            throw new IllegalArgumentException("Todo requires a title");

        }

        Integer sample_text= values.getAsInteger(TodoContract.TodoEntry.COLUMN_TODO_SAMPLE_TEXT);

        SQLiteDatabase database=mDbHelper.getWritableDatabase();

        long id=database.insert(TodoContract.TodoEntry.TABLE_NAME,null,values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }
        // Once we know the ID of the new row in the table,
        // return the new URI with the ID appended to the end of it
        return ContentUris.withAppendedId(uri,id) ;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }
}

