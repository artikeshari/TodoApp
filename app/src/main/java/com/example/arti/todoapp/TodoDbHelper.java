package com.example.arti.todoapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bandriya on 18-Feb-18.
 */
public class TodoDbHelper extends SQLiteOpenHelper {
    public static final int version= 1;
    public static final String DatabaseName="Todos.db";
    public static final String CREATE_STATEMENT="CREATE TABLE "+TodoContract.TodoEntry.TABLE_NAME+" ("
            +TodoContract.TodoEntry.COLUMN_TODO_ID+ " INTEGER NOT NULL PRIMARY KEY, "
            +TodoContract.TodoEntry.COLUMN_TODO_TITLE+ " TEXT NOT NULL, "
            +TodoContract.TodoEntry.COLUMN_TODO_SAMPLE_TEXT+ " TEXT);";
    public static final String DROP_STATEMENT="DELETE TABLE IF EXIST "+TodoContract.TodoEntry.TABLE_NAME+";";

    public TodoDbHelper(Context context) {
        super(context, DatabaseName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_STATEMENT);
    }
}
