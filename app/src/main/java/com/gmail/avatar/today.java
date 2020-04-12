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
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class today extends AppCompatActivity {
    ArrayList<Element> elements = new ArrayList<>();
    ArrayList<Element> full_elements = new ArrayList<>();
    ArrayList<Task> select_tasks = new ArrayList<>();
    ArrayList<Task> full_tasks = new ArrayList<>();
    ImageButton B_menu;
    ArrayList<CheckBox> checkBoxes;
    TextView countText;
    int taskCompletedCount = 0;
    int day = 0;
    int at = 0;
    int size;
    public static final String PREFS_NAME = "MyPrefsFile";
    public static final String TASK_PREFS_NAME = "MyPrefsFile";
    public static final String FULL_TASK_PREFS_NAME = "FullMyPrefsFile";
    String[] vals;
    Element E_fire;
    Element E_water;
    Element E_earth;
    Element E_air;

    int countTasks = 0;

    Time time = new Time();
    MainActivity ma = new MainActivity();
    SharedPreferences.Editor editor;
    SharedPreferences task_Settings;
    SharedPreferences full_task_settings;
    SharedPreferences settings;
    public static final String DAY_PREFS_NAME = "MyPrefsFile2";

    WelcomeScreen ws = new WelcomeScreen();
    ArrayList<String> fireT;
    ArrayList<String> waterT;
    ArrayList<String> earthT;
    ArrayList<String> airT;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);

        fireT = new ArrayList<String>();
        waterT = new ArrayList<String>();
        earthT = new ArrayList<String>();
        airT = new ArrayList<String>();
        String text = "";
        try {
            InputStream is = getAssets().open("tasks.txt");
            size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        vals = text.split("\n");
        addTasks(fireT, 3);
        addTasks(waterT, 3);
        addTasks(earthT, 3);
        addTasks(airT, 3);

        E_fire = new Element("fire", fireT);
        E_water = new Element("water", waterT);
        E_earth = new Element("earth", earthT);
        E_air = new Element("air", airT);

        elements.add(E_fire);
        elements.add(E_water);
        elements.add(E_earth);
        elements.add(E_air);


        settings = getSharedPreferences(DAY_PREFS_NAME, 0);
        day = settings.getInt("date", day);
        checkBoxes = new ArrayList<CheckBox>();

        countText = findViewById(R.id.countDisplay);
        SharedPreferences settings1 = getSharedPreferences(PREFS_NAME, 0);
        task_Settings = getSharedPreferences(TASK_PREFS_NAME, 0);
        full_task_settings = getSharedPreferences(FULL_TASK_PREFS_NAME, Context.MODE_PRIVATE);


        if (settings1.contains("count")) {
            taskCompletedCount = settings1.getInt("count", taskCompletedCount);
        } else {
            taskCompletedCount = 0;
        }


        System.out.println(time.getDay() + " " + day);
        countText.setText(String.valueOf(taskCompletedCount));

        B_menu = findViewById(R.id.menuBtn);
        B_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });

        LinearLayout linearLayout = findViewById(R.id.LinearLayout);


        if (time.getDay() == day) {
            for (Element e : elements) {
                full_elements.add(e);
            }
            select_tasks = new ArrayList<>();

            for (Element e : full_elements) {
                for (Task task : e.getFullTasks()) {
                    if (task_Settings.contains(task.getTaskName())) {
                        System.out.println("ADDED: " + (task.getTaskName()));
                        select_tasks.add(task);
                        e.addSelectedTask(task);
                        countTasks++;
                    }
                }
            }
            if(countTasks==0){
                final TextView tv=new TextView(this);
                tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                tv.setGravity(Gravity.CENTER);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(40, 20, 40, 20);
                tv.setLayoutParams(params);
                tv.setBackgroundResource(R.drawable.rounded_button);
                tv.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                tv.setText("Good job Avatar! Your mission for day is complete; go take a break!\n\nFor more tasks to complete, check out the list of tasks in the side menu.");
                tv.setPadding(50, 80, 50, 80);
                tv.setTextColor(this.getResources().getColor(R.color.colorPrimaryDark));
                tv.setElevation(20);
                tv.setTextSize(24);
                if (linearLayout != null) {
                    linearLayout.addView(tv);
                }
            }
        } else {
            elements = WelcomeScreen.getElements();
            full_elements = WelcomeScreen.getElements();
            for (Element e : full_elements) {
                for (Task task : e.getFullTasks()) {
                    editor = full_task_settings.edit();
                    editor.putString(task.getTaskName(), task.getTaskName());
                    editor.commit();
                }
            }
        }
        day = time.getDay();
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("date", day);
        System.out.println(day + " GOT DATE: " + settings.getInt("date", day));
        editor.commit();


        for (Element e : elements) {
            ArrayList<Task> temp = e.getSelectedTasks();
            for (final Task t : temp) {
                System.out.println("HAS THINGS");
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
                checkBox.setElevation(20);
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
                    taskCompletedCount++;
                    SharedPreferences settings1 = getSharedPreferences(PREFS_NAME, 0);
                    SharedPreferences.Editor editor = settings1.edit();
                    editor.putInt("count", taskCompletedCount);
                    editor.commit();
                    countText.setText(String.valueOf(taskCompletedCount));
                    e.removeTask(ta);
                    select_tasks.remove(ta);
                    editor = task_Settings.edit();
                    editor.remove(ta.getTaskName());
                    editor.commit();

                    if(taskCompletedCount >= 3){
                        countText.setText("Tier 1: " +String.valueOf(taskCompletedCount));
                    }else if(taskCompletedCount >= 6){
                        countText.setText("Tier 2: " +String.valueOf(taskCompletedCount));
                    }else if(taskCompletedCount >= 9){
                        countText.setText("Tier 3: " +String.valueOf(taskCompletedCount));
                    }else if(taskCompletedCount == 12){
                        countText.setText("Master: " +String.valueOf(taskCompletedCount));
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
                    editor = task_Settings.edit();
                    editor.putString(ta.getTaskName(), ta.getTaskName());
                    editor.commit();

                }
            }
        }
        elements = full_elements;
    }

    public void addTasks(ArrayList<String> a, int n) {
        at++;
        for (int i = 0; i < n; i++) {
            a.add(vals[at]);
            at++;
        }

    }
}
