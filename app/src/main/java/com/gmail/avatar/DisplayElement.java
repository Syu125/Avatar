package com.gmail.avatar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DisplayElement extends AppCompatActivity {
ArrayList<String>list;
    ArrayList<TextView>tasks;
   LinearLayout linearLayout;
   ArrayList<String>info = new ArrayList<>();
    ImageButton b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_element);

        linearLayout  =  findViewById(R.id.rootLayout);
        tasks = new ArrayList<TextView>();
            final TextView tv=new TextView(this);
            tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.CENTER);
            tv.setText("1. Stay away from screens for an hour\n" +
                    "2. Defrost your food in the fridge, not with the microwave\n" +
                    "3. Don't use a hairdryer");
            tv.setTextSize(40);
            if (linearLayout != null) {
                linearLayout.addView(tv);
            }

        b = findViewById(R.id.menuBtnn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });
    }
    public void openMenu(){
        Intent i = new Intent(this, Menu.class);
        startActivity(i);
    }
}
