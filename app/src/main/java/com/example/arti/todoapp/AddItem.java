package com.example.arti.todoapp;

import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItem extends AppCompatActivity {

    EditText mTitleEditText;
    EditText mSampleTextEditText;
    Button save;
    Button reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        // Find all relevant views that we will need to read user input from
        mTitleEditText = (EditText) findViewById(R.id.edit_title);
        mSampleTextEditText = (EditText) findViewById(R.id.sample_edit_text);
        save= (Button)findViewById(R.id.save);
        reset= (Button)findViewById(R.id.reset);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertTodo();
                finish();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTitleEditText.setText("");
                mSampleTextEditText.setText("");
            }

        });
    }
    private void insertTodo()
    {
        //TodoDbHelper TodoDbHelper=new TodoDbHelper(this);
        //SQLiteDatabase sqLiteDatabase=TodoDbHelper.getWritableDatabase();

        String TodoTitle=mTitleEditText.getText().toString().trim();
        String TodoSampleText=mSampleTextEditText.getText().toString().trim();

        ContentValues contentValues=new ContentValues();
        contentValues.put(TodoContract.TodoEntry.COLUMN_TODO_TITLE,TodoTitle);
        contentValues.put(TodoContract.TodoEntry.COLUMN_TODO_SAMPLE_TEXT,TodoSampleText);
       

        Uri newUri =getContentResolver().insert(TodoContract.TodoEntry.CONTENT_URI,contentValues);

        if(newUri!=null)
            Toast.makeText(this, "editor_insert_Todo_successful",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"editor_insert_Todo_failed",Toast.LENGTH_SHORT).show();

    }
}
