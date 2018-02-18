package com.example.arti.todoapp;

/**
 * Created by bandriya on 18-Feb-18.
 */
public class TodoItem {
    String title;
    String sample_text;

    public TodoItem(String title, String sample_text) {

        this.title = title;
        this.sample_text = sample_text;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSample_text() {
        return sample_text;
    }

    public void setSample_text(String sample_text) {
        this.sample_text = sample_text;
    }


    public String get_title()
    {return this.title;}
    public String get_sample_text()
    {return this.sample_text;}
}
