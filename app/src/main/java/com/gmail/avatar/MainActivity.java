package com.gmail.avatar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CrunchifyTimer(10);

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
        }
    }
    }

