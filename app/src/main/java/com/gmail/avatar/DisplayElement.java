package com.gmail.avatar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DisplayElement extends AppCompatActivity {
ArrayList<String>list;
    ArrayList<TextView>tasks;
   LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_element);


    }
    public DisplayElement(ArrayList<String> a){

        tasks = new ArrayList<TextView>();
        for(String s: a){
            final TextView tv=new TextView(this);
            tv.setText(s);
            linearLayout.addView(tv);
        }


    }
}
