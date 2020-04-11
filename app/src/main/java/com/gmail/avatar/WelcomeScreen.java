package com.gmail.avatar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import java.util.ArrayList;
import java.util.Scanner;


public class WelcomeScreen extends AppCompatActivity {
    ArrayList<String> fireT;
    ArrayList<String> waterT;
    ArrayList<String> earthT;
    ArrayList<String> airT;
    Element E_fire;
    Element E_water;
    Element E_earth;
    Element E_air;

    Button testB;


    LinearLayout linearLayout;
    ArrayList<CheckedTextView>ctvs;

    int num = 3;
    int result = 0;
    int at = 1;
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
                syso
            }
        });
        linearLayout = findViewById(R.id.LinearLayout);
        ctvs =  new ArrayList<CheckedTextView>();


        ArrayList<String> selections = new ArrayList<String>();
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
        vals = text.split(" ");

        addTasks(fireT,3);
        addTasks(waterT,3);
        addTasks(earthT,3);
        addTasks(airT,3);

        E_fire =  new Element(fireT);
        E_water =  new Element(waterT);
        E_earth =  new Element(earthT);
        E_air =  new Element(airT);

    }

    public void addTasks(ArrayList<String> a, int n) {
        at+=2;
        for (int i = 0; i < n; i++) {
            a.add(vals[at]);
            final CheckedTextView ctv =  new CheckedTextView(this);
            ctv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,  ViewGroup.LayoutParams.WRAP_CONTENT));
            ctv.setWidth(1200);
            ctv.setText(vals[at]);
            ctv.setChecked(false);
            ctv.setCheckMarkDrawable(android.R.drawable.checkbox_off_background);
            ctv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ctv.setChecked(!ctv.isChecked());
                    ctv.setCheckMarkDrawable(ctv.isChecked()? android.R.drawable.checkbox_on_background : android.R.drawable.checkbox_off_background);
                    for (String s: fireT) {
                        if(ctv.getText().equals(s)){
                            E_fire.addSelectedTask(E_fire.getTask(s));
                        }
                    }
                    for (String s: waterT) {
                        if(ctv.getText().equals(s)){
                            E_water.addSelectedTask(E_water.getTask(s));
                        }
                    }
                    for (String s: earthT) {
                        if(ctv.getText().equals(s)){
                            E_earth.addSelectedTask(E_earth.getTask(s));
                        }
                    }
                    for (String s: airT) {
                        if(ctv.getText().equals(s)){
                            E_air.addSelectedTask(E_air.getTask(s));
                        }
                    }

                }
            });
            if(linearLayout != null){
                linearLayout.addView(ctv);
            }
            ctvs.add(ctv);
            at++;
        }
    }



}

