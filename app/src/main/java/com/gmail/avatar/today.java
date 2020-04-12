package com.gmail.avatar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class today extends AppCompatActivity {
    ArrayList<Element> elements = new ArrayList<>();
    ImageButton B_menu;
    ArrayList<CheckBox> checkBoxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);

        checkBoxes = new ArrayList<CheckBox>();


        B_menu = findViewById(R.id.menuBtn);
        B_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });

        LinearLayout linearLayout = findViewById(R.id.LinearLayout);

        System.out.println("hi");

        elements = WelcomeScreen.getElements();
        System.out.println(elements);
        for (Element e : elements) {
            ArrayList<Task> temp = e.getSelectedTasks();
            for (final Task t : temp) {
                // Create Checkbox Dynamically
                CheckBox checkBox = new CheckBox(this);
                checkBox.setText(t.getTaskName());
                checkBox.setWidth(1300);
                checkBox.setHeight(200);
                checkBox.setBackgroundColor(Color.WHITE);
                checkBox.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                if(linearLayout != null){
                    linearLayout.addView(checkBox);
                }
                checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        completedTask(t);
                    }
                });
                checkBoxes.add(checkBox);
                System.out.println(t.getTaskName());

            }
        }
    }

    public void openMenu() {
        Intent i = new Intent(this, Menu.class);
        startActivity(i);
    }
    public void completedTask(Task t){
        Element[] elements_temp = new Element[elements.size()];
        for(int i = 0; i < elements.size();i++){
            elements_temp[i] =  elements.get(i);
        }
        for(Element e: elements_temp){
            ArrayList<Task>tempTasks = e.getTasks();
            Task[] tasks_temp = new Task[tempTasks.size()];
            for(int i = 0; i < tempTasks.size();i++){
                tasks_temp[i] =  tempTasks.get(i);
            }
            for(Task ta: tasks_temp){
                if(ta.getTaskName().equals(t.getTaskName()){
                    e.removeTask(ta);
            }
        }
        for(Element e: elements){
            ArrayList<Task>tempTasks = e.getTasks();
            for(Task ta: tempTasks){
                System.out.println(ta.getTaskName());
            }
        }
    }


}
