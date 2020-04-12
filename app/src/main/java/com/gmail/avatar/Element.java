package com.gmail.avatar;

import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class Element {
    SharedPreferences settings;
    ArrayList<String> tasks;
    ArrayList<Task> full_taskClass;
    ArrayList<Task> taskClass;
    ArrayList<Task> selected;
    ArrayList<Task> done;
    String name;


    public Element() {
        tasks = new ArrayList<String>();
        full_taskClass = new ArrayList<Task>();
        taskClass = new ArrayList<Task>();
        done = new ArrayList<Task>();
        selected = new ArrayList<Task>();
    }

    public Element(String name, ArrayList<String> t) {

        full_taskClass = new ArrayList<Task>();
        tasks = new ArrayList<String>();
        taskClass = new ArrayList<Task>();
        done = new ArrayList<Task>();
        selected = new ArrayList<Task>();
        tasks = t;
        convertToTask();
        this.name = name;
    }

    public void addSelectedTask(Task t) {
        selected.add(t);
    }

    public ArrayList<Task> getSelectedTasks() {
        return selected;
    }

    public void addTask(String task) {
        tasks.add(task);
    }

    public Task getTask(String name) {
        for (Task t : taskClass) {
            if (t.getTaskName().equals(name))
                return t;
        }
        return null;
    }

    public String getElementName() {
        return name;
    }

    public void convertToTask() {
        for (String s : tasks) {
            taskClass.add(new Task(s));
            full_taskClass.add(new Task(s));
        }
    }
    public void setTaskClassEqual(){
        taskClass = new ArrayList<Task>();
        for (Task t:full_taskClass) {
            taskClass.add(t);
        }

    }

    public void removeTask(Task t) {
        taskClass.remove(t);
    }
    public void addTask(Task t) {
        taskClass.remove(t);
    }

    public ArrayList<Task> getTasks() {
        return taskClass;
    }
    public ArrayList<Task> getFullTasks() {
        return full_taskClass;
    }


    public String getTasksString() {
        String s = "";
        for (String t : tasks) {
            s += "\n" + t;
        }
        return s;
    }


}
