package com.helptabteam.helptab;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by dell on 7/14/2016.
 */
public class MyAlarmService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        showNotification(getApplicationContext());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void showNotification(Context context) {
        Log.i("notification", "visible");
        Calendar calendar = Calendar.getInstance();


        SimpleDateFormat ti=new SimpleDateFormat("hh:mm:ss");
        String time=ti.format(calendar.getTime())+"";


       /* Cursor cursor=context.getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI,new String[]{QuoteColumns.TITLE},QuoteColumns.START + " = ?",new String[]{time},null);

        String title;
        if(cursor.getCount()!=0){
            title= cursor.getString(cursor.getColumnIndex("title"));
            cursor.close();*/
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);

            // build notification
            // the addAction re-use the same intent to keep the example short
            Notification n  = new Notification.Builder(context)
                    .setContentTitle("xyz")
                    .setContentText("Subject")
                    .setSmallIcon(R.drawable.iconslogo)
                    .setContentIntent(pIntent)
                    .setAutoCancel(true)
                    .build();


            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

            notificationManager.notify(0, n);
    //    }


    }

}
