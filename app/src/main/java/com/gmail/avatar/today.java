package com.gmail.avatar;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class today extends AppCompatActivity {
//WelcomeScreen ws;
//ArrayList<Elem>
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);

//        ws = new WelcomeScreen();
//        ws.getElements()

        System.out.println(elements);
        for(Element e: elements){
            System.out.println("Name: " + e.getElementName());
            ArrayList<Task> temp =  e.getSelectedTasks();
            for(Task t: temp){
                System.out.println(t.getTaskName());
            }
        }
    }

}
