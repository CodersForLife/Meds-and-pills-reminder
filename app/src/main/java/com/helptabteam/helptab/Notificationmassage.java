package com.helptabteam.helptab;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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

        SimpleDateFormat ti=new SimpleDateFormat("hh:mm:ss");
        String time=ti.format(calendar.getTime());


        Cursor cursor=context.getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI,new String[]{QuoteColumns.START},null,null,null);

        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            do {
                String databse_time=cursor.getString(cursor.getColumnIndex("start"));
                if(time.equalsIgnoreCase(databse_time)) {
                    Notification n  = new Notification.Builder(context)
                            .setContentTitle(cursor.getString(cursor.getColumnIndex("title")))
                            .setContentText(cursor.getString(cursor.getColumnIndex("description")))
                            .setSmallIcon(R.drawable.iconslogo)
                            .setAutoCancel(true)
                            .setSound( RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                            .build();


                    NotificationManager notificationManager =
                            (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

                    notificationManager.notify(0, n);
                }}while (cursor.moveToNext());
        }
        cursor.close();
    }
}
