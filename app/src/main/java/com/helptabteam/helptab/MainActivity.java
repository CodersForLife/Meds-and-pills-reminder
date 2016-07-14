package com.helptabteam.helptab;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button skip;
    Calendar cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NotificationFunction();
        final SharedPreferences sp=getSharedPreferences("Pref",MODE_PRIVATE);
        if (sp.contains("name1")) {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
        }
        setContentView(R.layout.activity_main);
        skip= (Button) findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(MainActivity.this,EmergencyContact.class));
                finish();
            }
        });
    }

    private void NotificationFunction() {

        Calendar calendar = Calendar.getInstance();


        SimpleDateFormat ti=new SimpleDateFormat("hh:mm:ss");
        String time=ti.format(calendar.getTime());


        Cursor cursor=getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI,new String[]{QuoteColumns.START},null,null,null);
        //cursor.close();
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            do {
                String databse_time=cursor.getString(cursor.getColumnIndex("start"));
                if(time.equalsIgnoreCase(databse_time)){
                    int hr = Integer.parseInt(databse_time.substring(0, 1));
                    int min = Integer.parseInt(databse_time.substring(3, 4));
                    int sec = Integer.parseInt(databse_time.substring(6, 7));
                    cal.set(Calendar.HOUR_OF_DAY, hr);
                    cal.set(Calendar.MINUTE, min);
                    cal.set(Calendar.SECOND, sec);
                    Intent intent = new Intent(this, Notificationmassage.class);

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis() , pendingIntent);
                }
            }while (cursor.moveToNext());
        }
        cursor.close();

      /*  long currentTime = b.getTime(); // Only call once, to be consistent
        long minAbsDiff = Long.MAX_VALUE;
        WhateverType minContainer = null;
        for (WhateverType x : a) {
            long abs = Math.abs(x.getTime() - currentTime);
            if (abs < minAbsDiff) {
                minAbsDiff = abs;
                minContainer = x;
            }
        }*/

     /*   long current_time=0;
        Date date2= null;
        try {
            date2 = ti.parse(time);
            current_time=date2.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long min=0,intermediate=0;

       if(cursor.getCount()!=0){
           cursor.moveToFirst();
           do {
               String date = cursor.getString(cursor.getColumnIndex("start"));
               long database_time;
               try {

                   Date mDate=ti.parse(date);
                   database_time=mDate.getTime();
                   intermediate=Math.abs(current_time-database_time);
                   if(cursor.isFirst())
                       min=intermediate;
                   if(min>intermediate)
                       min=database_time;

               } catch (ParseException e) {
                   e.printStackTrace();
               }

           }while (cursor.moveToNext());
           cursor.close();*/


         /*  int hr = Integer.parseInt(date.substring(0, 1));
           int min = Integer.parseInt(date.substring(3, 4));
           int sec = Integer.parseInt(date.substring(6, 7));
           cal.set(Calendar.HOUR_OF_DAY, hr);
           cal.set(Calendar.MINUTE, min);
           cal.set(Calendar.SECOND, sec);*/

               //cal.setTimeInMillis(Math.abs(min));



                  }


    }


