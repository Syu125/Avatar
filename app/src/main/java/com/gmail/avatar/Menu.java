package com.gmail.avatar;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Menu extends AppCompatActivity {
    LinearLayout mainLayout;
    PopupMenu popUp;
    WelcomeScreen ws;
    ArrayList<Element> elements = new ArrayList<Element>();

    int num = 3;
    int result = 0;
    int at = 0;
    int size = 0;
    Element E_fire;
    Element E_water;
    Element E_earth;
    Element E_air;
    ArrayList<String> fireT;
    ArrayList<String> waterT;
    ArrayList<String> earthT;
    ArrayList<String> airT;
    LinearLayout linearLayout;
    String[] vals;
    ArrayList<CheckedTextView> ctvs;
    DisplayElement ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ws = new WelcomeScreen();


        linearLayout = findViewById(R.id.LinearLayout);
        ctvs = new ArrayList<CheckedTextView>();

        mainLayout = new LinearLayout(this);


        Button B_Fire = findViewById(R.id.fire);
        Button B_Water = findViewById(R.id.water);
        Button B_Earth = findViewById(R.id.earth);
        Button B_Air = findViewById(R.id.air);
        Button B_Today = findViewById(R.id.today);
        ImageButton B_menu = findViewById(R.id.menuBtn);

        popUp = new PopupMenu(Menu.this, B_Fire);


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


        B_Fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayElement();
            }
        });
        B_Water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayElementW();

            }
        });
        B_Earth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayElementE();

            }
        });
        B_Air.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayElementA();

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


    public void openElementTasks(String name) {
        String s = "";
        for (Element e : elements) {
            if (e.getElementName().equals(name)) {
                s = e.getTasksString();
            }
        }
        TextView tv = new TextView(this);
        // System.out.println("OUT: " + s);

        tv.setText(s);
        popUp.show();
    }

    public void addTasks(ArrayList<String> a, int n) {
        at++;
        for (int i = 0; i < n; i++) {
            a.add(vals[at]);

            at++;
        }


    }

    public void openDisplayElement() {
        Intent i = new Intent(this, DisplayElement.class);
        startActivity(i);
    }
    public void openDisplayElementA() {
        Intent i = new Intent(this, DisplayElementA.class);
        startActivity(i);
    }
    public void openDisplayElementE() {
        Intent i = new Intent(this, DisplayElementE.class);
        startActivity(i);
    }
    public void openDisplayElementW() {
        Intent i = new Intent(this, DisplayElementW.class);
        startActivity(i);
    }
}
