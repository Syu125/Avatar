package com.gmail.avatar;

import java.util.ArrayList;

public class Element {
    ArrayList<Task>tasks;
    ArrayList<Task>done;
    public Element(){
        tasks = new ArrayList<Task>();
        done =  new ArrayList<Task>();
    }
    public Element(ArrayList<Task>tasks){
        this.tasks =  tasks;
    }
    public void addTask(Task task){
        tasks.add(task);
    }
}
