package com.helptabteam.helptab;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dell on 7/13/2016.
 */
public class Notificationmassage extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
       /* Intent service1 = new Intent(context, MyAlarmService.class);
        context.startService(service1);*/
        showNotification(context);
    }

    private void showNotification(Context context) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND,00);

        SimpleDateFormat ti=new SimpleDateFormat("HH:mm:ss");
        String time=ti.format(calendar.getTime());


        Cursor cursor=context.getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI,new String[]{QuoteColumns._ID,QuoteColumns.START,QuoteColumns.DATE,QuoteColumns.INTERVAL},null,null,null);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String current_date = df.format(c.getTime());

        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            do {
                String databse_time=cursor.getString(cursor.getColumnIndex("start"));
                if(time.equalsIgnoreCase(databse_time) && cursor.getString(cursor.getColumnIndex("date")).equalsIgnoreCase(current_date)) {
                    Notification n  = new Notification.Builder(context)
                            .setContentTitle(cursor.getString(cursor.getColumnIndex("title")))
                            .setContentText(cursor.getString(cursor.getColumnIndex("description")))
                            .setSmallIcon(R.drawable.iconslogo)
                            .setAutoCancel(true)
                            .setSound( RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                            .build();
                    String date=cursor.getString(cursor.getColumnIndex("date"));
                    Date se = null;
                    try {
                        se=new SimpleDateFormat("dd-MMM-yyyy").parse(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    c.setTime(se);
                    if(cursor.getInt(cursor.getColumnIndex("interval"))==1)
                        c.add(Calendar.DATE,1);
                    else if(cursor.getInt(cursor.getColumnIndex("interval"))==7)
                        c.add(Calendar.DATE,7);
                    else
                        c.add(Calendar.MONTH,1);

                    ContentValues contentValues = new ContentValues();
                    contentValues.put(QuoteColumns.DATE,df.format(c.getTime())+"");
                    context.getContentResolver().update(QuoteProvider.Quotes.CONTENT_URI,contentValues,QuoteColumns.TITLE+" = ?",new String[]{cursor.getInt(cursor.getColumnIndex("_id"))+""});
                    NotificationManager notificationManager =
                            (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

                    notificationManager.notify(0, n);
                }}while (cursor.moveToNext());
        }
        cursor.close();
    }
}

