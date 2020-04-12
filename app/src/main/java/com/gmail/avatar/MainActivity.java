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
    int day = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        schedule = new Time();
        System.out.println("BEFORE: " +day);
        day = schedule.getDay();
        System.out.println("AFTER: " + day);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        if (settings.contains("date")) {
            System.out.println(settings.getInt("date",day));
            if(day != settings.getInt("date", day)){
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        /* Create an Intent that will start the Menu-Activity. */
                        Intent mainIntent = new Intent(MainActivity.this,WelcomeScreen.class);
                        MainActivity.this.startActivity(mainIntent);
                        MainActivity.this.finish();
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
                }
            }, SPLASH_DISPLAY_LENGTH);
        }
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("date", day);
        editor.commit();

        //openMenu();

       /* CrunchifyTimer(10);

        Intent intent = new Intent(this, WelcomeScreen.class);
        startActivity(intent);


    }
    public void CrunchifyTimer(int seconds){
      timer =  new Timer();
        timer.schedule(new NextTask(), seconds * 1000);
    }

    class NextTask extends  TimerTask{
        @Override
        public void run(){
            System.out.println("Terminated");
            timer.cancel();
        }*/
    }
    public void openMenu(){
        Intent intent = new Intent(this, WelcomeScreen.class);
        startActivity(intent);
    }
    }

