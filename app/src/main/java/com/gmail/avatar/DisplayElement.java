package com.gmail.avatar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayElement extends AppCompatActivity {
    ArrayList<String> list;
    ArrayList<TextView> tasks;
    LinearLayout linearLayout;
    ArrayList<String> info = new ArrayList<>();
    ImageButton b;
    TextView elementName;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_element);

        elementName = findViewById(R.id.navbar);
        elementName.setText("Fire");

        linearLayout = findViewById(R.id.rootLayout);
        tasks = new ArrayList<TextView>();
        final TextView tv = new TextView(this);
        tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tv.setGravity(Gravity.CENTER);
        tv.setText("1. Stay away from screens for an hour\n\n" +
                "2. Defrost your food in the fridge, not with the microwave\n\n" +
                "3. Don't use a hairdryer");
        tv.setTextSize(24);
        tv.setGravity(Gravity.LEFT);
        tv.setTextColor(this.getResources().getColor(R.color.colorPrimaryDark));
        if (linearLayout != null) {
            linearLayout.addView(tv);
        }

        b = findViewById(R.id.menuBtn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });
    }

    public void openMenu() {
        Intent i = new Intent(this, Menu.class);
        startActivity(i);
    }
}
