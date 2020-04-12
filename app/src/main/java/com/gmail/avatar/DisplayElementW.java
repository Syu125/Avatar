package com.gmail.avatar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DisplayElementW extends AppCompatActivity {
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
            tv.setText("1. Do the dishes with your faucet off\n" +
                    "2. Turn the water off while brushing your teeth\n" +
                    "3. Finish showering in 3 minutes");
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
