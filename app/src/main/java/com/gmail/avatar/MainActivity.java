package com.gmail.avatar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.os.Handler;


import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private Timer timer;
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    Time schedule;
    public static final String PREFS_NAME = "MyPrefsFile2";
    public static final String VISIT_PREFS_NAME = "MyVisitPrefsFile2";
    int day = 10;
    SharedPreferences settings;
    SharedPreferences visit_settings;;
    int visited = 0; //0 false, 1 true
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        schedule = new Time();
        System.out.println("BEFORE: " +day);
        day = schedule.getDay();
        System.out.println("AFTER: " + day);
        settings = getSharedPreferences(PREFS_NAME, 0);
        visit_settings = getSharedPreferences(PREFS_NAME, 0);

        visited =  visit_settings.getInt("visited", visited);
        if (settings.contains("date")) {
            if(day != settings.getInt("date", day)){
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        /* Create an Intent that will start the Menu-Activity. */
                        Intent mainIntent = new Intent(MainActivity.this,WelcomeScreen.class);
                        MainActivity.this.startActivity(mainIntent);
                        MainActivity.this.finish();
                        visited = 0;
                        SharedPreferences.Editor editor = visit_settings.edit();
                        editor.putInt("visited", visited);
                        editor.commit();
                    }
                }, SPLASH_DISPLAY_LENGTH);
            }else{
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        /* Create an Intent that will start the Menu-Activity. */
                        Intent mainIntent = new Intent(MainActivity.this,today.class);
                        MainActivity.this.startActivity(mainIntent);
                        MainActivity.this.finish();
                        visited = 1;
                        SharedPreferences.Editor editor = visit_settings.edit();
                        editor.putInt("visited", visited);
                        editor.commit();
                    }
                }, SPLASH_DISPLAY_LENGTH);
            }

        } else {
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    /* Create an Intent that will start the Menu-Activity. */
                    Intent mainIntent = new Intent(MainActivity.this,WelcomeScreen.class);
                    MainActivity.this.startActivity(mainIntent);
                    MainActivity.this.finish();
                    visited = 1;
                    SharedPreferences.Editor editor = visit_settings.edit();
                    editor.putInt("visited", visited);
                    editor.commit();
                }
            }, SPLASH_DISPLAY_LENGTH);
        }
        System.out.println("GETTT: " + visited);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("date", day);
        editor.commit();


    }
    /*public boolean visited(){
        visit_settings = getSharedPreferences(PREFS_NAME, 0);
        int i  = visit_settings.getInt("visited",visited);
        if(i == 0){
            return false;
        }
        return true;
    }*/
    public void openMenu(){
        Intent intent = new Intent(this, WelcomeScreen.class);
        startActivity(intent);
    }
    }

