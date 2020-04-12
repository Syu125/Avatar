package com.gmail.avatar;

import android.content.Intent;
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
    CheckBox c1, c2, c3;
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
            for (Task t : temp) {

                // Create Checkbox Dynamically
                CheckBox checkBox = new CheckBox(this);
                checkBox.setText(t.getTaskName());
                checkBox.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                System.out.println(t.getTaskName());
            }
        }
    }

    public void openMenu() {
        Intent i = new Intent(this, Menu.class);
        startActivity(i);
    }


}
