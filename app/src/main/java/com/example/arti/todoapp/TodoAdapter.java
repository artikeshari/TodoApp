package com.example.arti.todoapp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bandriya on 18-Feb-18.
 */
public class TodoAdapter extends ArrayAdapter<TodoItem> {

    public TodoAdapter(Context context, ArrayList<TodoItem> TodoItems) {
        super(context,0,TodoItems);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link TodoItem} object located at this position in the list
        TodoItem currentTodoItem = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID title_text_view.
        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);

        titleTextView.setText(currentTodoItem.get_title());

        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        TextView sampleTextView = (TextView) listItemView.findViewById(R.id.sample_text);
        // Get the default translation from the currentTodoItem object and set this text on
        // the default TextView.
        sampleTextView.setText(currentTodoItem.get_sample_text());

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}
