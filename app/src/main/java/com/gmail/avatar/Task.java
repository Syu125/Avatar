package com.gmail.avatar;

import android.widget.CheckedTextView;
import android.widget.LinearLayout;

public class Task {
    CheckedTextView ctv;
    String task;
    LinearLayout linearLayout;
    public Task(String task){
        this.task = task;
    }
    public String getTaskName(){
        return task;
    }
}
