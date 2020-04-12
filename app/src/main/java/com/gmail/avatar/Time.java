package com.gmail.avatar;
import java.util.Calendar;
import java.util.Date;

public class Time {
    private Date currentTime;
    private Calendar calendar;
    int day;
    public Time(){
        calendar = Calendar.getInstance();
        System.out.println("DATE: " + calendar.get(Calendar.DAY_OF_WEEK));
        currentTime = calendar.getTime();
    }
    public Date getDate(){
       // System.out.println(currentTime.);
        return currentTime;
    }
    public int getDay(){
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

}
