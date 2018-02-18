package com.example.arti.todoapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] projection={
                TodoContract.TodoEntry.COLUMN_TODO_ID,
                TodoContract.TodoEntry.COLUMN_TODO_TITLE,
                TodoContract.TodoEntry.COLUMN_TODO_SAMPLE_TEXT,
        };
        Cursor cursor=getContentResolver().query(TodoContract.TodoEntry.CONTENT_URI,projection,null,null,null);

        // make arrayList of ListItems
        final ArrayList<TodoItem> TodoItems = new ArrayList<TodoItem>();
        try {

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(TodoContract.TodoEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(TodoContract.TodoEntry.COLUMN_TODO_TITLE);
            int breedColumnIndex = cursor.getColumnIndex(TodoContract.TodoEntry.COLUMN_TODO_SAMPLE_TEXT);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentTitle = cursor.getString(nameColumnIndex);
                String currentSampleText = cursor.getString(breedColumnIndex);

                TodoItems.add(new TodoItem(currentTitle, currentSampleText));
            }
        }finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
//
//        TodoItems.add( new TodoItem("three copy","for school"));
//        TodoItems.add( new TodoItem("Dress","For daughter"));
//        TodoItems.add( new TodoItem("cake","for birthday party"));



        // Create an {@link TodoAdapter}, whose data source is a list of {@link Todo}s. The
        // adapter knows how to create list items for each item in the list.
        TodoAdapter adapter = new TodoAdapter(this, TodoItems);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // Todo_list.xml layout file.
        ListView listView = (ListView)findViewById(R.id.list);

        // Make the {@link ListView} use the {@link TodoAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Todo} in the list.
        listView.setAdapter(adapter);

         // make button to add new data
            button=(Button)findViewById(R.id.Add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddItem.class);
                startActivity(intent);
            }
        });
    }
}

