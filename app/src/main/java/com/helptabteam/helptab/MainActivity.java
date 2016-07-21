package com.helptabteam.helptab;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
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

        Calendar c=Calendar.getInstance();
        int min=c.get(Calendar.MINUTE);

        if(min>=0 && min<15)
            min=0;
        else if(min>=15 && min<30)
            min=15;
        else if(min>=30 && min<45)
            min=30;
        else if(min>=45 && min<60)
            min=45;
        c.set(Calendar.MINUTE,min);
        c.set(Calendar.SECOND,00);

        AlarmManager alarmManager=(AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, Notificationmassage.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),60000,
                pendingIntent);
    }


    }


