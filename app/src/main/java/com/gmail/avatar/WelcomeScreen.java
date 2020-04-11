package com.gmail.avatar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class WelcomeScreen extends AppCompatActivity {
ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.gmail.avatar.R.layout.activity_welcome_screen);

       // listview = findViewById(R.id.selection);

        ArrayList<String>selections =  new ArrayList<String>();
String text = "";
try{
    InputStream is =  getAssets().open("tasks.txt");
    int size =  is.available();
    System.out.println(size);
    byte [] buffer =  new byte[size];
    is.read(buffer);
    is.close();
    text =  new  String(buffer);
} catch (IOException e) {
    e.printStackTrace();
}
System.out.println(text);




    }
}
