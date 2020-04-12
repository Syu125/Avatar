package com.gmail.avatar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class today extends AppCompatActivity {
    ArrayList<Element> elements = new ArrayList<>();
    ArrayList<Element> full_elements = new ArrayList<>();
    ArrayList<Task> select_tasks = new ArrayList<>();
    ImageButton B_menu;
    ArrayList<CheckBox> checkBoxes;
    TextView countText;
    int taskCompletedCount = 0;
    int day = 0;
    public static final String PREFS_NAME = "MyPrefsFile";
    public static final String TASK_PREFS_NAME = "MyPrefsFile";

    Time time =  new Time();
    MainActivity ma = new MainActivity();
    SharedPreferences.Editor editor;
    SharedPreferences task_Settings;
    SharedPreferences settings;
    public static final String DAY_PREFS_NAME = "MyPrefsFile2";


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);

        settings = getSharedPreferences(DAY_PREFS_NAME, 0);
        day = settings.getInt("date", day);
        System.out.println("HIIII" + day);
        checkBoxes = new ArrayList<CheckBox>();

        countText = findViewById(R.id.countDisplay);
        SharedPreferences settings1 = getSharedPreferences(PREFS_NAME, 0);
        task_Settings = getSharedPreferences(TASK_PREFS_NAME, 0);




        if (settings1.contains("count")) {
            taskCompletedCount = settings1.getInt("count", taskCompletedCount);
        } else {
            taskCompletedCount = 0;
        }


        System.out.println(taskCompletedCount + " " + day);
        countText.setText(String.valueOf(taskCompletedCount));

        B_menu = findViewById(R.id.menuBtn);
        B_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });

        LinearLayout linearLayout = findViewById(R.id.LinearLayout);

        elements = WelcomeScreen.getElements();
        full_elements = WelcomeScreen.getElements();
        if(time.getDay()==day){
            for(Element e: full_elements){
                for(Task task: e.getFullTasks()){
                    if(task_Settings.contains(task.getTaskName())){
                        select_tasks.add(task);
                    }
                }
            }
        }else{
            elements = WelcomeScreen.getElements();
            full_elements = WelcomeScreen.getElements();
        }
       /* if(!ma.visited()){
            elements = WelcomeScreen.getElements();
            full_elements = WelcomeScreen.getElements();
        }else{
            for(Element e: full_elements){
                for(Task task: e.getFullTasks()){
                    if(task_Settings.contains(task.getTaskName())){
                        select_tasks.add(task);
                    }
                }
            }
        }*/


        for (Element e : elements) {
            ArrayList<Task> temp = e.getSelectedTasks();
            for (final Task t : temp) {
                select_tasks.add(t);
                editor = task_Settings.edit();
                editor.putString(t.getTaskName(), t.getTaskName());
                editor.commit();
                // Create Checkbox Dynamically
                final CheckBox checkBox = new CheckBox(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(40, 20, 40, 20);
                checkBox.setLayoutParams(params);
                checkBox.setText(t.getTaskName());
                checkBox.setTextSize((float) 16);
                checkBox.setTextColor(getResources().getColorStateList(R.color.colorPrimaryDark));
                checkBox.setWidth(1300);
                checkBox.setHeight(250);
                checkBox.setBackgroundResource(R.drawable.rounded_button);
                checkBox.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                checkBox.setGravity(Gravity.CENTER_VERTICAL);

                checkBox.setPadding(10, 10, 10, 20);
                if (linearLayout != null) {
                    linearLayout.addView(checkBox);
                }
                checkBox.setChecked(false);
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (checkBox.isChecked()) {
                            System.out.println("CHECKED");
                            completedTask(t);
                        } else {
                            System.out.println("HIT");
                            addTask(t);
                        }
                    }
                });

                checkBoxes.add(checkBox);

            }
        }
    }

    public void openMenu() {
        Intent i = new Intent(this, Menu.class);
        startActivity(i);
    }

    public void importTasks() {

    }

    public void completedTask(Task t) {
        Element[] elements_temp = new Element[elements.size()];
        for (int i = 0; i < elements.size(); i++) {
            elements_temp[i] = elements.get(i);
        }
        for (Element e : elements_temp) {
            ArrayList<Task> tempTasks = e.getTasks();
            Task[] tasks_temp = new Task[tempTasks.size()];
            for (int i = 0; i < tempTasks.size(); i++) {
                tasks_temp[i] = tempTasks.get(i);
            }
            for (Task ta : tasks_temp) {
                if (ta.getTaskName().equals(t.getTaskName())) {
                    taskCompletedCount=0;
                    SharedPreferences settings1 = getSharedPreferences(PREFS_NAME, 0);
                    SharedPreferences.Editor editor = settings1.edit();
                    editor.putInt("count", taskCompletedCount);
                    editor.commit();
                    countText.setText(String.valueOf(taskCompletedCount));
                    e.removeTask(ta);
                    select_tasks.remove(ta);
                    editor.clear();
                    for (Task task : select_tasks) {
                        editor = task_Settings.edit();
                        editor.putString(t.getTaskName(), t.getTaskName());
                        editor.commit();
                    }

                }
            }
        }

    }

    public void addTask(Task t) {
        Element[] elements_temp = new Element[full_elements.size()];
        for (int i = 0; i < full_elements.size(); i++) {
            elements_temp[i] = full_elements.get(i);
        }
        for (Element e : elements_temp) {
            ArrayList<Task> tempTasks = e.getFullTasks();
            Task[] tasks_temp = new Task[tempTasks.size()];
            for (int i = 0; i < tempTasks.size(); i++) {
                tasks_temp[i] = tempTasks.get(i);
            }
            for (Task ta : tasks_temp) {
                if (ta.getTaskName().equals(t.getTaskName())) {
                    System.out.println("SUBTRACTED");
                    taskCompletedCount--;
                    SharedPreferences settings1 = getSharedPreferences(PREFS_NAME, 0);
                    SharedPreferences.Editor editor = settings1.edit();
                    editor.putInt("count", taskCompletedCount);
                    editor.commit();
                    countText.setText(String.valueOf(taskCompletedCount));
                    e.addTask(t);
                    e.setTaskClassEqual();
                    select_tasks.add(t);
                    editor.clear();
                    for (Task task : select_tasks) {
                        editor = task_Settings.edit();
                        editor.putString(t.getTaskName(), t.getTaskName());
                        editor.commit();
                    }
                }
            }
        }
        elements = full_elements;
    }


}
