package com.gmail.avatar;

import java.util.ArrayList;

public class Element {
    ArrayList<String> tasks;
    ArrayList<Task> taskClass;
    ArrayList<Task> selected;
    ArrayList<Task> done;

    public Element() {
        tasks = new ArrayList<String>();
        taskClass = new ArrayList<Task>();
        done = new ArrayList<Task>();
        selected = new ArrayList<Task>();
    }

    public Element(ArrayList<String> t) {
        tasks = new ArrayList<String>();
        taskClass = new ArrayList<Task>();
        done = new ArrayList<Task>();
        selected = new ArrayList<Task>();
        tasks = t;
        convertToTask();

    }

    public void addSelectedTask(Task t) {
        selected.add(t);
        System.out.println("Got task: " + t.getTaskName());
    }

    public void addTask(String task) {
        tasks.add(task);
    }
    public Task getTask(String name){
        for (Task t: taskClass) {
            if(t.getTaskName().equals(name))
                return t;
        }
        return null;
    }

    public void convertToTask() {
        for (String s : tasks) {
            taskClass.add(new Task(s));
        }
    }
}
