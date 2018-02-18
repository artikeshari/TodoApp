package com.example.arti.todoapp;

import android.net.Uri;
import android.provider.BaseColumns;


public class TodoContract {

    public static final String CONTENT_AUTHORITY = "com.example.android.todos";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_TODOS = "todos";
    public static final class TodoEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_TODOS);

        public static final String TABLE_NAME = "todos";
        public static final String COLUMN_TODO_ID = BaseColumns._ID;
        public static final String COLUMN_TODO_TITLE = "Title";
        public static final String COLUMN_TODO_SAMPLE_TEXT = "SampleText";
    }
}
