package com.gmail.avatar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;


public class WelcomeScreen extends AppCompatActivity {
    ArrayList<String> fireT;
    ArrayList<String> waterT;
    ArrayList<String> earthT;
    ArrayList<String> airT;

    private static ArrayList<Element> elements = new ArrayList<Element>();
    Element E_fire;
    Element E_water;
    Element E_earth;
    Element E_air;

    Button testB;



    LinearLayout linearLayout;
    ArrayList<CheckedTextView> ctvs;

    int num = 3;
    int result = 0;
    int at = 0;
    int size = 0;
    String[] vals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.gmail.avatar.R.layout.activity_welcome_screen);
        fireT = new ArrayList<String>();
        waterT = new ArrayList<String>();
        earthT = new ArrayList<String>();
        airT = new ArrayList<String>();


        testB = findViewById(R.id.button);
        testB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Task> temp = new ArrayList<Task>();
                ArrayList<Task> get = new ArrayList<Task>();
                get = E_fire.getSelectedTasks();
                for(Task t: get){
                    temp.add(t);
                }
                get = E_water.getSelectedTasks();
                for(Task t: get){
                    temp.add(t);
                }
                get = E_earth.getSelectedTasks();
                for(Task t: get){
                    temp.add(t);
                }
                get = E_air.getSelectedTasks();
                for(Task t: get){
                    temp.add(t);
                }
                openToday();
            }
        });
        linearLayout = findViewById(R.id.LinearLayout);
        ctvs = new ArrayList<CheckedTextView>();


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

    }
    public void openToday(){
        Intent intent = new Intent(this, today.class);
        startActivity(intent);
    }

    int checkFinal = 1;
    public void addTasks(ArrayList<String> a, int n) {
        at ++;
        for (int i = 0; i < n; i++) {
            a.add(vals[at]);
            final CheckedTextView ctv = new CheckedTextView(this);
            ctv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ctv.setWidth(1500);
            ctv.setGravity(Gravity.CENTER_VERTICAL);
            ctv.setHeight(1000);
            ctv.setTextSize((float) 20);
            ctv.setBackgroundResource(R.drawable.border);
            ctv.setTextColor(this.getResources().getColor(R.color.colorPrimaryDark));
            ctv.setText(vals[at]);
            ctv.setChecked(false);
            ctv.setCheckMarkDrawable(android.R.drawable.checkbox_off_background);

            if (checkFinal == vals.length - 9) {
                // TODO: THIS CODE MAY NO LONGER WORK AS THINGS ARE ADDED.
                ctv.setPadding(80, 80, 80, 80);
            } else {
                ctv.setPadding(80, 80, 80, 0);
                checkFinal++;
            }

            ctv.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(View v) {
                    ctv.setChecked(!ctv.isChecked());
                    ctv.setCheckMarkDrawable(ctv.isChecked() ? android.R.drawable.checkbox_on_background : android.R.drawable.checkbox_off_background);
                    if (ctv.isChecked()) {
                        ctv.setBackgroundResource(R.drawable.border_selected);
                    } else {
                        ctv.setBackgroundResource(R.drawable.border);
                    }

                    // Checks Tasks
                    for (String s : fireT) {
                        if (ctv.getText().equals(s)) {
                            E_fire.addSelectedTask(E_fire.getTask(s));
                        }
                    }
                    for (String s : waterT) {
                        if (ctv.getText().equals(s)) {
                            E_water.addSelectedTask(E_water.getTask(s));
                        }
                    }
                    for (String s : earthT) {
                        if (ctv.getText().equals(s)) {
                            E_earth.addSelectedTask(E_earth.getTask(s));
                        }
                    }
                    for (String s : airT) {
                        if (ctv.getText().equals(s)) {
                            E_air.addSelectedTask(E_air.getTask(s));
                        }
                    }

                }
            });
            if (linearLayout != null) {
                linearLayout.addView(ctv);
            }
            ctvs.add(ctv);
            at++;
        }
    }


    public Element getElement(String name) {
        for (Element e : elements) {
            if (e.getElementName().equals(name))
                return e;
        }

        return null;
    }
public static ArrayList<Element>getElements(){
        return  elements;
}

}

