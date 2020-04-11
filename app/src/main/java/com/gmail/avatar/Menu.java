package com.gmail.avatar;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class Menu extends AppCompatActivity {
    LinearLayout mainLayout;
    PopupWindow popUp;
    WelcomeScreen ws;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ws = new WelcomeScreen();

        mainLayout = new LinearLayout(this);
        popUp = new PopupWindow(this);
        Button B_Fire = findViewById(R.id.fire);
        Button B_Water = findViewById(R.id.water);
        Button B_Earth = findViewById(R.id.earth);
        Button B_Air = findViewById(R.id.air);
        Button B_Today = findViewById(R.id.today);
        ImageButton B_menu = findViewById(R.id.menuBtn);

        B_Fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFireTasks();
            }
        });
        B_Water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        B_Earth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        B_Air.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        B_Today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openToday();
            }
        });
        B_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openToday();
            }
        });
    }

    public void openToday() {
        Intent intent = new Intent(this, today.class);
        startActivity(intent);
    }

    public void addTasks() {

    }

    public void openFireTasks() {
    openElementTasks("fire");
    }

    public void openWaterTasks() {

    }

    public void openEarthTasks() {

    }

    public void openElementTasks(String name) {
        Element currentElement = ws.getElement(name);
        TextView tv = new TextView(this);
        System.out.println("OUT: " + currentElement.getTasksString());
        tv.setText(currentElement.getTasksString());
        popUp.setContentView(new TextView(this));
    }


}
